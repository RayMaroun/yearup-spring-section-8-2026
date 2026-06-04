package com.pluralsight;

import com.pluralsight.data.ProductDao;
import com.pluralsight.models.Product;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/Northwind";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "P@ssw0rd";

    public static void main(String[] args) {
        DataSource dataSource = createDataSource();
        ProductDao productDao = new ProductDao(dataSource);
        Scanner scanner = new Scanner(System.in);

        /*System.out.print("Show products under what price? ");
        double maxPrice = scanner.nextDouble();

        List<Product> found = productDao.getUnderPrice(maxPrice);

//        for (Product product : found) {
//            System.out.println(product.getName() + " - $" + product.getPrice());
//        }

        found.forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));*/

        showMenu(scanner, productDao);

    }

    public static void showMenu(Scanner scanner, ProductDao productDao) {
        System.out.println("1) Find products under a price");
        System.out.println("2) Add a product");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Max price: ");
            double maxPrice = scanner.nextDouble();
            productDao.getUnderPrice(maxPrice).forEach(product -> System.out.println(product.getName() + " - $" + product.getPrice()));
        } else if (choice == 2) {
            scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Price: ");
            double price = scanner.nextDouble();
            productDao.add(name, price, 1);
        }
    }

/*    public static List<Product> searchProducts(Scanner scanner, DataSource dataSource) {
        List<Product> products = new ArrayList<>();
        System.out.print("Show products under what price? ");
        //String input = scanner.nextLine();
        double input = scanner.nextDouble();

        String sql = "SELECT ProductID, ProductName, UnitPrice FROM Products WHERE UnitPrice < ?";

//        Connection connection = null;
//
//        try {
//            connection = dataSource.getConnection();
//            System.out.println("Connected to the database!");
//        } catch (SQLException e) {
//            System.out.println("Could not connect: " + e.getMessage());
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    System.out.println("Error closing: " + e.getMessage());
//                }
//            }
//        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, input);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
//                String name = results.getString(2);
//                double price = results.getDouble(3);
                    products.add(new Product(
                            results.getInt("ProductID"),
                            results.getString("ProductName"),
                            results.getDouble("UnitPrice")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }

        return products;
    }*/

    private static DataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}
