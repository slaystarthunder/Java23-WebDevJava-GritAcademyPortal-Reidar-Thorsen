package com.example.javawebservicesdemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.javawebservicesdemo.dao.DatabaseConnector;
import com.example.javawebservicesdemo.dao.StudentDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userCourseServlet", urlPatterns = {"/users-courses-servlet"})
public class UserCourseServlet extends HttpServlet {

    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (request.getSession().getAttribute("userType").equals("teacher")) {
            StringBuilder resultMessage = new StringBuilder();
            resultMessage.append("<html><body>");
            resultMessage.append("<h1>Students with Courses</h1>");

            List<String> studentsCourses = studentDAO.getStudentsWithCourses();

            if (studentsCourses.isEmpty()) {
                resultMessage.append("<p>No students with courses found or error establishing connection.</p>");
            } else {
                resultMessage.append("<ul>");
                for (String studentCourse : studentsCourses) {
                    resultMessage.append("<li>").append(studentCourse).append("</li>");
                }
                resultMessage.append("</ul>");
            }

            resultMessage.append("<a href='index.html'>Home</a>");
            resultMessage.append("</body></html>");
            out.println(resultMessage.toString());
        } else {
            List<String> studentsCourses = new ArrayList<>();
            Connection conn = DatabaseConnector.getConnection();
            try {
                int userId = studentDAO.getUserId((String) request.getSession().getAttribute("username"));
                String query = "SELECT courses.name FROM user_courses " +
                        "INNER JOIN courses ON user_courses.courses_id = courses.id " +
                        "WHERE user_courses.users_id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    studentsCourses.add(resultSet.getString("name"));
                    System.out.println(resultSet.getString("name"));
                }



                // Building the response
                StringBuilder resultMessage = new StringBuilder();
                resultMessage.append("<html><body>");
                resultMessage.append("<h1>Your Courses</h1>");

                if (studentsCourses.isEmpty()) {
                    resultMessage.append("<p>No courses found or error establishing connection.</p>");
                } else {
                    resultMessage.append("<ul>");
                    for (String course : studentsCourses) {
                        resultMessage.append("<li>").append(course).append("</li>");
                    }
                    resultMessage.append("</ul>");
                }

                resultMessage.append("<a href='index.html'>Home</a>");
                resultMessage.append("</body></html>");
                out.println(resultMessage.toString());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
