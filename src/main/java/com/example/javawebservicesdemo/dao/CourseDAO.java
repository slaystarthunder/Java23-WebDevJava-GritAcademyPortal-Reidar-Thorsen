package com.example.javawebservicesdemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public List<String> getAllCourses() {
        Connection conn = DatabaseConnector.getConnection();
        ArrayList<String> courses = new ArrayList<>();
        if (conn != null) {
            String query = "SELECT * FROM courses";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String s = rs.getString(1) +" " +rs.getString(2) +" "+ rs.getString(3)+ " " +" "+ rs.getString(4);
                    courses.add(s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courses;
    }

    public boolean addUserToCourse(int userId, int courseId) {
        Connection conn = DatabaseConnector.getConnection();
        boolean success = false;

        if (conn != null) {
            String query = "INSERT INTO user_courses (users_id, courses_id) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, courseId);
                success = stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public void addCourse(String name, String description, int YHP) {
        Connection conn = DatabaseConnector.getConnection();
        if (conn != null) {
            String query = "INSERT INTO courses (name, description, yhp) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setInt(3, YHP);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
