package dao;

import connection.GetConnection;
import model.Clients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    public Clients insertClient(Clients client) {
        String sql = "INSERT INTO Clients(name, email) VALUES(?, ?);";
        try (
                Connection connection = GetConnection.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                }
            }

            return client;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClientName(Clients client) {
        String sql = "UPDATE Clients SET name = ? WHERE id = ?;";
        try (

                Connection connection = GetConnection.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, client.getName());
            stmt.setInt(2, client.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateClientEmail(Clients client) {
        String sql = "UPDATE Clients SET email = ? WHERE id = ?;";
        try (Connection connection = GetConnection.createConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, client.getEmail());
            stmt.setInt(2, client.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void softDeleteClient(int id) {
        String sql = "UPDATE Clients SET active = FALSE WHERE id = ?;";

        try (
                Connection connection = GetConnection.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Clients searchByID(int id) {
        String sql = "SELECT * FROM Clients WHERE id = ?;";
        try (
                Connection connection = GetConnection.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Clients(rs.getString("name"), rs.getString("email"), rs.getInt("id"));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Clients> listAll() {
        List<Clients> clients = new ArrayList<>();
        String sql = "SELECT * FROM Clients WHERE active = TRUE;";
        try (
                Connection connection = GetConnection.createConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    clients.add(new Clients(rs.getString("name"), rs.getString("email"), rs.getInt("id")));
                }
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}