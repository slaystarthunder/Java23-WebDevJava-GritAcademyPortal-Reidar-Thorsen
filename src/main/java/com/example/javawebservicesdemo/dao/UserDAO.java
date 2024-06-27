package com.example.javawebservicesdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean validate(String username, String password) {
        Connection conn = DatabaseConnector.getConnection();
        boolean status = false;

        if (conn != null) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                status = rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public void register(String username, String password, String userType) {
        Connection conn = DatabaseConnector.getConnection();

        if (conn != null) {
            String query = "INSERT INTO Users (username, password, userType, state) VALUES (?, ?, ?, 'confirmed')";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, userType);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserType(String username) {
        Connection conn = DatabaseConnector.getConnection();
        String userType = null;

        if (conn != null) {
            String query = "SELECT user_type FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    userType = rs.getString("user_type");
                    System.out.println(userType);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(userType);
        return userType;
    }

}
