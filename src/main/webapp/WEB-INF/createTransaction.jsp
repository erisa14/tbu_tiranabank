<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Create Transaction</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<c:if test="${not empty transactionError}">
    <p style="color: red;">${transactionError}</p>
</c:if>
<form:form modelAttribute="newTransaction" action="/createTransaction" method="post" class="form gap-3 d-flex flex-column">
    <c:if test="${!empty errors}">
        <ul style="color: red;">
            <c:forEach items="${errors.allErrors}" var="error">
                <li><c:out value="${error.defaultMessage}" /></li>
            </c:forEach>
        </ul>
    </c:if>
    <div>
        <form:errors path="creditAccount" class="errors text-danger"/>
        <form:label path="creditAccount">Credit Account:</form:label>
        <form:input path="creditAccount" type="text" class="form-control"/>
    </div>
    <div>
        <form:errors path="description" class="errors text-danger"/>
        <form:label path="description">Description:</form:label>
        <form:input path="description" type="text" class="form-control"/>
    </div>
    <div>
        <form:errors path="amount" class="errors text-danger"/>
        <form:label path="amount">Amount:</form:label>
        <form:input path="amount" type="text" class="form-control"/>
    </div>
    <div>
        <form:errors path="debitAccount" class="errors text-danger"/>
        <form:label path="debitAccount">Debit Account:</form:label>
        <form:select path="debitAccount" class="form-control">
            <c:forEach items="${accounts}" var="account">
                <option value="${account.id}" >${account.accountNumber}</option>
        </c:forEach>
        </form:select>
    </div>


    <input class="w-50 btn btn-primary" type="submit" value="Create">

</form:form>
</body>
</html>
