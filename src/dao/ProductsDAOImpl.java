package dao;

import connection.GetConnection;
import model.Products;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAOImpl implements ProductsDAO {

    public Products insertProducts(Products products) {
        String sql = "INSERT INTO Products(name, price, stock) VALUES(?, ?, ?);";
        try (Connection connection = GetConnection.createConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, products.getName());
            stmt.setBigDecimal(2, products.getPrice());
            stmt.setInt(3, products.getStock());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    products.setId(1);
                }else{
                    throw new SQLException("No id generated!");
                }
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProductsPrice(int id, BigDecimal newPrice) {
        String sql = "UPDATE Products SET price = ? WHERE id = ?;";
        try (Connection connection = GetConnection.createConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setBigDecimal(1, newPrice);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProductsName(int id, String newName) {
        String sql = "UPDATE Products SET name = ? WHERE id = ?;";
        try (Connection connection = GetConnection.createConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStock(int id, int newStock) {
        String sql = "UPDATE Products SET stock = ? WHERE id = ?;";
        try (Connection connection = GetConnection.createConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, newStock);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProducts(Products products) {
        String sql = "UPDATE Products SET name = ?, price = ?, stock = ? WHERE id = ?;";
        try (Connection connection = GetConnection.createConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, products.getName());
            stmt.setBigDecimal(2, products.getPrice());
            stmt.setInt(3, products.getStock());
            stmt.setInt(4, products.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Products searchById(int id) {
        String sql = "SELECT * FROM Products WHERE id = ?;";
        try (Connection connection = GetConnection.createConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Products(rs.getString("name"), rs.getBigDecimal("price"), rs.getBoolean("available"), rs.getInt("id"), rs.getInt("stock"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void softDeleteProducts(int id) {
        String sql = "UPDATE Products SET available = FALSE WHERE id = ?;";
        try (Connection connection = GetConnection.createConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Products> listAll() {
        List<Products> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE available = TRUE;";
        try (Connection connection = GetConnection.createConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Products(rs.getString("name"), rs.getBigDecimal("price"), rs.getBoolean("available"), rs.getInt("id"), rs.getInt("stock")));
                }
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}