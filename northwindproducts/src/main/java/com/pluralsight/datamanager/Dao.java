package com.pluralsight.datamanager;

import com.pluralsight.model.Product;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Dao {
    BasicDataSource dataSource;
    public Dao(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }
       public Scanner input = new Scanner(System.in);
    ArrayList<Product> products = new ArrayList<>();
    public ArrayList<Product> getAll(){

        System.out.print("All Products:");
        String sql1 = """
                SELECT * 
                FROM products;
                """;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
            //result set goes in nested try
            //execute the statement/query above nested try
            try (ResultSet results = preparedStatement.executeQuery()) {
                // process the results
                while (results.next()) {
                    String productId = results.getString("productid");
                    String productName = results.getString("productname");
                    double unitPrice = results.getDouble("unitprice");
                    int unitsInStock = results.getInt("unitsinstock");
                }
            }
        } catch (SQLException e) {
            System.out.println("there was a problem getting information.");
            e.printStackTrace();
        }
        return products;
    }
    private ArrayList<Product> getFilmBySearch(){

        System.out.print("Search for product that start with: ");
        String searchTerm = input.next() + "%";
        String sql1 = """
                SELECT * 
                FROM products
                WHERE ProductName like ?;
                """;
        try (Connection connection = this.dataSource.getConnection();
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
                    Product product = new Product(productId, productName, unitPrice, unitsInStock);
                    products.add(product);
                    System.out.printf("%-4s %-30s %10.2f %4d \n", productId, productName, unitPrice, unitsInStock);
                }
            }
        } catch (SQLException e) {
            System.out.println("there was a problem getting information.");
            e.printStackTrace();
        }
        return products;
    }
    public void createProduct(Product product){

    }

    public void updateProduct(int productId, Product product){

    }

    public void deleteProduct(int productId){

    }
}
