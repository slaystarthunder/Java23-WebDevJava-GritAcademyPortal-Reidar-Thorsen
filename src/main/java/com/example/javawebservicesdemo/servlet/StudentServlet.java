package com.example.javawebservicesdemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.javawebservicesdemo.dao.StudentDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "studentServlet", urlPatterns = {"/students-servlet"})
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    public void init(ServletConfig config) throws ServletException {
        studentDAO = new StudentDAO();
        super.init(config);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(request.getSession().getAttribute("userType").equals("teacher")) {

        }
        else{


        }
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("<html><body>");
        resultMessage.append("<h1>Students List</h1>");

        List<String> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            resultMessage.append("<p>No students found or error establishing connection.</p>");
        } else {
            resultMessage.append("<ul>");
            for (String student : students) {
                resultMessage.append("<li>").append(student).append("</li>");
            }
            resultMessage.append("</ul>");
        }

        resultMessage.append("<a href='index.html'>Home</a>");
        resultMessage.append("</body></html>");
        out.println(resultMessage.toString());
    }
}
