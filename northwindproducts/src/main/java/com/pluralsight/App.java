package com.pluralsight;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException{
    // 1. open a connection to the database
// use the database URL to point to the correct database
        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <username> <password>");
            System.exit(1);

        }
        String username = args[0];
        String password = args[1];
// get the user-name and password from the command line args
       try(
        Connection connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/northwind",username, password);
        PreparedStatement preparedStatement =
                connection.prepareStatement(
                        " SELECT productid, " +
                                "productname, " +
                                "unitprice, " +
                                "unitsinstock " +
                                "FROM products;")){
    ResultSet results = preparedStatement.executeQuery();
// process the results
while (results.next()) {
    String productId = results.getString("productid");
    String productName = results.getString("productname");
    double unitPrice = results.getDouble("unitprice");
    int unitsInStock = results.getInt("unitsinstock");
    System.out.printf("%-40s, %-40s, %.2f, %d \n", productId, productName, unitPrice, unitsInStock);
//            results.getString(1), results.getString(2),
//            results.getString(3), results.getString(4);
    }

}      catch(SQLException e){
           System.out.println("there was a problem getting information.");
           e.printStackTrace();
       }
    }}
