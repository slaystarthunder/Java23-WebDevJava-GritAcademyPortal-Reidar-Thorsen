package com.example.javawebservicesdemo.servlet;

import com.example.javawebservicesdemo.dao.CourseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="course-servlet", urlPatterns="/courses")
public class CourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CourseDAO dao = new CourseDAO();

        ArrayList<String> courseList = new ArrayList<>();

        courseList = (ArrayList<String>) dao.getAllCourses();
        req.getSession().setAttribute("courseList", courseList);
        this.getServletContext().getRequestDispatcher("/course-list.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
