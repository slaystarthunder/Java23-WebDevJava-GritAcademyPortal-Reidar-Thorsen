<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Teacher Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h2>Teacher Dashboard</h2>
<p>Welcome, ${username}!</p>
<h3>Your Courses</h3>
<ul>
    <!-- Loop through the courses and display them -->
    <c:forEach var="course" items="${courses}">
        <li>${course}</li>
    </c:forEach>
</ul>
<h3>Manage Students</h3>
<ul>
    <!-- Loop through the students and display them -->
    <c:forEach var="student" items="${students}">
        <li>${student}</li>
    </c:forEach>
</ul>
<a href="logout">Logout</a>
</body>
</html>
