<%-- 
    Document   : welcome
    Created on : Oct 23, 2020, 6:32:48 PM
    Author     : Alkis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello USER!</h1>
        <a href="/home">Home</a> 
        <form:form method="POST" action="${pageContext.request.contextPath}/logout">
            <input type="submit" value="Logout">
        </form:form>

    </body>
</html>
