<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User Accounts</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
</head>
<body style="background-color: #183c7c">
<jsp:include page="navbar.jsp" />
<div class="content">
<table class="table table-striped border w-75 mt-5 mx-auto bd-white p-5" style="border-color: #f4ba20!important;border-width: 12px!important;">
    <thead>
    <tr>
        <th class="px-4" >Account Number</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
    <tr>

        <td class="fs-5 px-4 fw-medium"><c:out value="${account.accountNumber}"/></td>
        <td>
        <a href="/account/${account.id}/details">Get Account Details</a> </td>
        <td>
            <a href="/accountTransactions/${account.id}">Get Account Transaction</a> </td>


    </tr>
    </c:forEach>


    </tbody>
</table>
</div>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
