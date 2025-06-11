package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {

        // use the database URL to point to the correct database
        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <username> <password>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Search for product that start with: ");
        String searchTerm = scanner.nextLine() + "%";
        String sql1 = """
                SELECT * 
                FROM products
                WHERE ProductName like ?;
                """;
            // connection and prep statement goes in outer try
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
            //result set goes in nested try
            //execute the statement/query above nested try
            preparedStatement.setString(1, searchTerm);
            try (ResultSet results = preparedStatement.executeQuery()) {
                    // print header top of nested try
                System.out.printf("%-4s %-30s %-10s %-10s%n", "Id", "name", "unitprice", "In Stock");
                System.out.println("_____________________________________________________________");
                // process the results
                while (results.next()) {
                    String productId = results.getString("productid");
                    String productName = results.getString("productname");
                    double unitPrice = results.getDouble("unitprice");
                    int unitsInStock = results.getInt("unitsinstock");
                    System.out.printf("%-4s %-30s %10.2f %4d \n", productId, productName, unitPrice, unitsInStock);
                }
            }
        } catch (SQLException e) {
            System.out.println("there was a problem getting information.");
            e.printStackTrace();
        }
    }
}
