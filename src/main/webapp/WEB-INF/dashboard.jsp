<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body style="background-color: #183c7c">
<jsp:include page="navbar.jsp"/>

<div class="mx-auto mt-5">
    <h4 class="text-center" style="color: #f4ba20">Welcome, ${user.firstname} ${user.lastname}</h4>
    <h1 class="text-center fw-bold" style="color: #f4ba20">Get started with your Bank Experience!</h1>
</div>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
