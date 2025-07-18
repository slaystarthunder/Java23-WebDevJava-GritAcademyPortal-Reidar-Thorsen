package com.example.javawebservicesdemo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StudentDAO {

    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        Connection conn = DatabaseConnector.getConnection();

        if (conn != null) {
            String query = "SELECT * FROM students";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    students.add(rs.getString("fName") + " " + rs.getString("lName"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    public List<String> getStudentsWithCourses() {
        List<String> studentsCourses = new ArrayList<>();
        Connection conn = DatabaseConnector.getConnection();

        if (conn != null) {
            String query = "SELECT users.fname, users.lname, courses.name  FROM user_courses " +
            "INNER JOIN courses ON user_courses.courses_id = courses.id INNER JOIN users ON user_courses.users_id=users.id";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    studentsCourses.add(rs.getString("fName") + " " +
                            rs.getString("lName") + " - " +
                            rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentsCourses;
    }

    public void addStudent(String fName, String lName, String town, String email, String phone, String username, String password) {
        Connection conn = DatabaseConnector.getConnection();
        if (conn != null) {
            String query = "INSERT INTO students (fName, lName, town, email, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, fName);
                stmt.setString(2, lName);
                stmt.setString(3, town);
                stmt.setString(4, email);
                stmt.setString(5, phone);
                stmt.setString(6, username);
                stmt.setString(7, password);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStudent(int id, String fName, String lName, String town, String email, String phone, String username, String password) {
        Connection conn = DatabaseConnector.getConnection();
        if (conn != null) {
            String query = "UPDATE students SET fName = ?, lName = ?, town = ?, email = ?, phone = ?, username = ?, password = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, fName);
                stmt.setString(2, lName);
                stmt.setString(3, town);
                stmt.setString(4, email);
                stmt.setString(5, phone);
                stmt.setString(6, username);
                stmt.setString(7, password);
                stmt.setInt(8, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteStudent(int id) {
        Connection conn = DatabaseConnector.getConnection();
        if (conn != null) {
            String query = "DELETE FROM students WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getUserId(String username) throws SQLException {
        Connection connection = DatabaseConnector.getConnection();


            if (connection != null) {

                String query = "SELECT id FROM users WHERE username = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);

                try ( ResultSet rs = statement.executeQuery()) {
                    rs.next();
                    return rs.getInt("id");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        return -1;
    }

    public boolean validateStudent(String username, String password) {
        Connection conn = DatabaseConnector.getConnection();
        if (conn != null) {
            String query = "SELECT * FROM students WHERE username = ? AND password = ?";
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

}
