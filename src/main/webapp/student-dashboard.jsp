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
    <title>Student Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h2>Student Dashboard</h2>
<p>Welcome, ${username}!</p>
<h3>Your Courses</h3>
<ul>
    <!-- Loop through the courses and display them -->
    <c:forEach var="course" items="${courses}">
        <li>${course}</li>
    </c:forEach>
</ul>
<a href="logout">Logout</a>
</body>
</html>
