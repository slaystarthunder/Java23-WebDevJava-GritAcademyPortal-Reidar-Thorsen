package com.example.javawebservicesdemo.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import com.example.javawebservicesdemo.dao.DatabaseConnector;
import com.example.javawebservicesdemo.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginServlet", urlPatterns = {"/login-servlet"})
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");



        if (userDAO.validate(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("state", "confirmed");
            session.setAttribute("userType",userDAO.getUserType(username));
            response.sendRedirect("userPage.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
