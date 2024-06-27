<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Courses</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<h2>Course List</h2>
<a href="add-course.jsp">Add New Course</a>
<!-- Logic to display list of courses -->
<ul>
<% if(request.getSession().getAttribute("courseList").equals(null))
    {

    } else {
    ArrayList<String> courseList = (ArrayList<String>) request.getSession().getAttribute("courseList");
         for (int i = 0 ; i < courseList.size() ; i++ )
         { %>   <li>() <%=courseList.get(i)%></li>




<%}
%>
</ul>
</body>
</html>
