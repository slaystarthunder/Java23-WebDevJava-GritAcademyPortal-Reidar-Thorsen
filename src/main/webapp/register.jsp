<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h2>Register</h2>
<form action="register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>
    <label for="userType">User Type:</label>
    <select id="userType" name="userType">
        <option value="student">Student</option>
        <option value="teacher">Teacher</option>
    </select><br>
    <button type="submit">Register</button>
</form>
</body>
</html>
