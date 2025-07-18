package com.example.javawebservicesdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {

    public boolean validateTeacher(String username, String password) {
        Connection conn = DatabaseConnector.getConnection();
        if (conn != null) {
            String query = "SELECT * FROM teachers WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String getPrivilegeType(String username) {
        Connection conn = DatabaseConnector.getConnection();
        String privilege = "user"; // fallback
        if (conn != null) {
            String query = "SELECT privilege_type FROM teachers WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    privilege = rs.getString("privilege_type");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return privilege;
    }
}
