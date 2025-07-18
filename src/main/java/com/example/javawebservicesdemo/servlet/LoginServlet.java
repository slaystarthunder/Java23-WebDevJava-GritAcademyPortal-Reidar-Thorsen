package com.example.javawebservicesdemo.servlet;

import java.io.IOException;

import com.example.javawebservicesdemo.dao.StudentDAO;
import com.example.javawebservicesdemo.dao.TeacherDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginServlet", urlPatterns = {"/login-servlet"})
public class LoginServlet extends HttpServlet {

    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;

    public void init() {
        studentDAO = new StudentDAO();
        teacherDAO = new TeacherDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if (studentDAO.validateStudent(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("state", "confirmed");
            session.setAttribute("userType", "student");
            response.sendRedirect("userPage.jsp");

        } else if (teacherDAO.validateTeacher(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("state", "confirmed");
            session.setAttribute("userType", "teacher");
            String privilege = teacherDAO.getPrivilegeType(username);
            session.setAttribute("privilegeType", privilege);
            response.sendRedirect("userPage.jsp");

        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
