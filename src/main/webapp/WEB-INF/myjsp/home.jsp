<%-- 
    Document   : welcome
    Created on : Oct 23, 2020, 6:32:48 PM
    Author     : Alkis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WELCOME Page</title>
    </head>
    <body>
        <h1>WELCOME</h1>
        
        <a href="/register">Register</a>

        <security:authorize access="hasRole('USER')">
            USER 
            <a href="/user">User page</a> 
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            ADMIN 
            <a href="/admin">Admin page</a> 
        </security:authorize>
        <security:authorize access="hasRole('VET')">
            VET 
            <a href="/Vet">Vet page</a> 
        </security:authorize>


        <security:authorize access="isAuthenticated()">
            <form:form method="POST" action="${pageContext.request.contextPath}/logout">
                <input type="submit" value="Logout">
            </form:form>
        </security:authorize>>

    </body>
</html>
