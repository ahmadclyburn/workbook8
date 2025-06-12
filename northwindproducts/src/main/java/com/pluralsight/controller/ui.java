package com.pluralsight.controller;

import com.pluralsight.datamanager.Dao;
import com.pluralsight.model.Product;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.ArrayList;
import java.util.Scanner;

import static com.pluralsight.App.input;

public class ui {
    public Scanner input =new Scanner(System.in);
    public void promptMenu (String username, String password){
        System.out.println("what would you like to do?");
        String userWants = input.next();

        switch (userWants) {
            case "1":
                displayAllProducts(dataSource);
                break;
            case "2":
                getFilmBySearch;
                break;
            case "3":
                createProduct;
                break;
            case "4":
                updateProduct;
                break;
            case "5":
                deleteProduct;
                break;
        }
    }
    private static void displayAllProducts(BasicDataSource dataSource) {
        Dao dao = new Dao(dataSource);
        ArrayList<Product> products = dao.getAll();

        //print header row
        System.out.printf("%-4s %-30s %-10s %-10s%n", "Id", "name", "unitprice", "In Stock");
        System.out.println("_____________________________________________________________");

        for (Product product:products){
            System.out.printf("%-4s %-30s %10.2f %4d \n", product.getProductId(), product.getProductName(),
                                                    product.getUnitPrice(), product.getUnitsInStock());
        }
    }
    private static void displaySearchedProduct(BasicDataSource dataSource){
        System.out.print("Search for films that start with: ");
        String searchTerm = input.nextLine() + "%";
        Dao dao = new Dao(dataSource);
        ArrayList<Product> products = dao.getAll();

    }



}
