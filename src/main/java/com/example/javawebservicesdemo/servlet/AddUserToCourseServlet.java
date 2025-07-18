package com.example.javawebservicesdemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.javawebservicesdemo.dao.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddUserToCourseServlet", value = "/add-user-to-course")
public class AddUserToCourseServlet extends HttpServlet {
    private CourseDAO courseDAO;

    public void init() {
        courseDAO = new CourseDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("users_id"));
        int courseId = Integer.parseInt(request.getParameter("courses_id"));

        boolean success = courseDAO.addUserToCourse(userId, courseId);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add User to Course</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
        out.println("</head>");
        out.println("<body>");
        if (success) {
            out.println("<h2>User successfully added to the course!</h2>");
        } else {
            out.println("<h2>Failed to add user to the course.</h2>");
            System.out.println(userId);
            System.out.println(courseId);
        }
        out.println("<a href=\"student-dashboard.jsp\">Back to Dashboard</a>");
        out.println("<a href=\"logout\">Logout</a>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add User to Course</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Add User to Course</h2>");
        out.println("<form action=\"add-user-to-course\" method=\"post\">");
        out.println("<label for=\"users_id\">User ID:</label><br>");
        out.println("<input type=\"number\" id=\"users_id\" name=\"users_id\" required><br><br>");
        out.println("<label for=\"courses_id\">Course ID:</label><br>");
        out.println("<input type=\"number\" id=\"courses_id\" name=\"courses_id\" required><br><br>");
        out.println("<input type=\"submit\" value=\"Add User to Course\">");
        out.println("</form>");
        out.println("<a href=\"userPage.jsp\">Back to Dashboard</a>");
        out.println("<a href=\"logout\">Logout</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
