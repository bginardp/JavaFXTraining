<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success Page</title>
</head>
<body>
<h1>Hola mon</h1>
<%

String name=(String)request.getAttribute("uname");
if(name!=null)
{
    %>
    <h1>Hi welcome <%=name %> </h1>
    <a href="/provajsp1/HomeServlet">Back to Login</a>
<%
} else { %>
<h1>No ha arribat el nom</h1>
<%
}
 %>

</body>
</html>