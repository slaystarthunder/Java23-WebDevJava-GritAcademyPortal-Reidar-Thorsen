package com.example.javawebservicesdemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.javawebservicesdemo.dao.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddCourseServlet", value = "/add-course")
public class AddCourseServlet extends HttpServlet {
    private CourseDAO courseDAO;

    public void init() {
        courseDAO = new CourseDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int yhp = Integer.parseInt(request.getParameter("yhp"));

        courseDAO.addCourse(name, description, yhp);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add Course</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Course successfully added!</h2>");
        out.println("<a href=\"userPage.jsp\">Back to Dashboard</a>");
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
        out.println("<title>Add Course</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Add New Course</h2>");
        out.println("<form action=\"add-course\" method=\"post\">");
        out.println("<label for=\"name\">Course Name:</label><br>");
        out.println("<input type=\"text\" id=\"name\" name=\"name\" required><br><br>");
        out.println("<label for=\"description\">Description:</label><br>");
        out.println("<textarea id=\"description\" name=\"description\" required></textarea><br><br>");
        out.println("<label for=\"yhp\">YHP:</label><br>");
        out.println("<input type=\"number\" id=\"yhp\" name=\"yhp\" required><br><br>");
        out.println("<input type=\"submit\" value=\"Add Course\">");
        out.println("</form>");
        out.println("<a href=\"userPage.jsp\">Back to Dashboard</a>");
        out.println("<a href=\"logout\">Logout</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
