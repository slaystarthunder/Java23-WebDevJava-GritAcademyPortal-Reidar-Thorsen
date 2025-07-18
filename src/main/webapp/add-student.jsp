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
    <title>Add Student</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h2>Add New Student</h2>
<form action="student-crud" method="post">
    <input type="hidden" name="action" value="add">
    <label for="fName">First Name:</label>
    <input type="text" id="fName" name="fName" required><br>
    <label for="lName">Last Name:</label>
    <input type="text" id="lName" name="lName" required><br>
    <label for="town">Town:</label>
    <input type="text" id="town" name="town" required><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>
    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <button type="submit">Add Student</button>
</form>
</body>
</html>
