package com.example.javawebservicesdemo.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;




@WebServlet(name = "helloServlet", urlPatterns = {"/hello-servlet"})
public class HelloServlet extends HttpServlet {

    private com.example.javawebservicesdemo.dao.CourseDAO courseDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        courseDAO = new com.example.javawebservicesdemo.dao.CourseDAO();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("<html><body>");
        resultMessage.append("<h1>Database Connection Test</h1>");

        List<String> courses = courseDAO.getAllCourses();

        if (courses.isEmpty()) {
            resultMessage.append("<p>Error establishing connection or no courses found.</p>");
        } else {
            resultMessage.append("<p>Connection established successfully.</p>");
            resultMessage.append("<h2>Courses</h2>");
            resultMessage.append("<ul>");
            for (String course : courses) {
                resultMessage.append("<li>").append(course).append("</li>");
            }
            resultMessage.append("</ul>");
        }

        resultMessage.append("</body></html>");
        out.println(resultMessage);
    }
}
