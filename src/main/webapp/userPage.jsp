<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jspf" %>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="container">
    <h2>Welcome, ${sessionScope.username}</h2>

            <p>You are logged in as a <%=session.getAttribute("userType")%></p>

            <%if(session.getAttribute("userType").equals("teacher")){%>
            <p><a href="users-courses-servlet">View Students with Courses</a></p>
            <p><a href="add-course">Add Course</a></p>
            <p><a href="add-user-to-course">Add User to Course</a></p>
            <%}
                else{%>
                <p><a href="users-courses-servlet">View Your Courses</a></p>
                    <%}%>

    <p><a href="logout">Logout</a></p>
</div>
<%@ include file="footer.jspf" %>
</body>
</html>
