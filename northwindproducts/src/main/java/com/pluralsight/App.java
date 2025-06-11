package com.pluralsight;

import java.net.SecureCacheResponse;
import java.sql.*;
import java.util.Scanner;

public class App {
    public static Scanner input =new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        String username = args[0];
        String password = args[1];
        // use the database URL to point to the correct database
        validateCredentials(args);

    }

    private static void validateCredentials(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <username> <password>");
            System.exit(1);
        }
    }

    public void promptMenu (String username, String password){
        System.out.println("what would you like to do?");
        String userWants = input.next();

        switch (userWants) {
            case "1":
                printAll(username, password);
                break;
            case "2":
                searchForLetter(username, password);
                break;
            case "3":
                break;
        }
    }
    public static void printAll(String username, String password){
        
        System.out.print("All Products:");
        String searchTerm = input.next() + "%";
        String sql1 = """
                SELECT * 
                FROM products;
                """;
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
    public static void searchForLetter(String username, String password){
        
        System.out.print("Search for product that start with: ");
        String searchTerm = input.next() + "%";
        String sql1 = """
                SELECT * 
                FROM products
                WHERE ProductName like ?;
                """;
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
