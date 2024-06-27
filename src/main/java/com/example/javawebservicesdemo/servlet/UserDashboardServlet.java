package com.example.javawebservicesdemo.servlet;



import java.io.IOException;
import java.util.List;
import com.example.javawebservicesdemo.dao.CourseDAO;
import com.example.javawebservicesdemo.dao.StudentDAO;
import com.example.javawebservicesdemo.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

@WebServlet(name = "user-dashboard-servlet",urlPatterns = {"/user-dashboard"})
public class UserDashboardServlet extends HttpServlet {
    private CourseDAO courseDAO;
    private StudentDAO studentDAO;
    private UserDAO userDAO;

    public void init() {
        courseDAO = new CourseDAO();
        studentDAO = new StudentDAO();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        String userType = userDAO.getUserType(username);

        List<String> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);
        request.setAttribute("username", username);

        if ("student".equals(userType)) {
            request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
        } else if ("teacher".equals(userType)) {
            List<String> students = studentDAO.getAllStudents();
            request.setAttribute("students", students);
            request.getRequestDispatcher("teacher-dashboard.jsp").forward(request, response);
        }
    }
}
