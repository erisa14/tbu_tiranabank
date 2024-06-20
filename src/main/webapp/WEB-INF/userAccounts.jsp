<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Accounts</title>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Account Number</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
    <tr>

        <td><c:out value="${account.accountNumber}"/></td>
        <td>
        <a href="/account/${account.id}/details">Get Account Details</a> </td>
        <td>
            <a href="/account/${account.id}/transactions">Get Account Transaction</a> </td>


    </tr>
    </c:forEach>


    </tbody>
</table>
</body>
</html>
