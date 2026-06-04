package com.pluralsight.data;

import com.pluralsight.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDao {

    private final DataSource dataSource;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> getUnderPrice(double maxPrice) {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT ProductID, ProductName, UnitPrice FROM Products WHERE UnitPrice < ?";

        /*Connection connection = null;

        try {
            connection = dataSource.getConnection();
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Could not connect: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing: " + e.getMessage());
                }
            }
        }*/

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, maxPrice);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                /*String name = results.getString(2);
                double price = results.getDouble(3);*/
                    products.add(new Product(
                            results.getInt("ProductID"),
                            results.getString("ProductName"),
                            results.getDouble("UnitPrice")));
                }
            }
        } catch (SQLException e) {
            System.out.println("getUnderPrice failed: " + e.getMessage());
        }

        return products;
    }

    public void add(String name, double price, int categoryId) {
        String sql = "INSERT INTO Products (ProductName, UnitPrice, CategoryID) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, categoryId);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Added product. Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("add failed: " + e.getMessage());
        }
    }

    public void updatePrice(int productId, double newPrice) {
        String sql = "UPDATE Products SET UnitPrice = ? WHERE ProductID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, newPrice);
            statement.setInt(2, productId);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Updated. Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("updatePrice failed: " + e.getMessage());
        }
    }

    public void delete(int productId) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Deleted. Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("delete failed: " + e.getMessage());
        }
    }
}
