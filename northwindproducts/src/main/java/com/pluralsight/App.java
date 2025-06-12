package com.pluralsight;

import java.sql.*;
import java.util.*;
import org.apache.commons.dbcp2.BasicDataSource;

public class App {
    public static Scanner input =new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        String username = args[0];
        String password = args[1];
        BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
                dataSource.setUsername(username);
                dataSource.setPassword(password);

    }

    public static void validateCredentials(String[] args) {
        if (args.length != 2) {
            System.out.println("Application needs two arguments to run: java com.pluralsight.UsingDriverManager <username> <password>");
            System.exit(1);
        }
    }




}
