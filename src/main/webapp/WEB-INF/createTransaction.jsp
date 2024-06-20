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

<form:form modelAttribute="transactionRequest" action="/create" method="post" class="form gap-3 d-flex flex-column">
<%--    <div>--%>
<%--        <form:errors path="credit_account" class="errors text-danger"/>--%>
<%--        <form:label path="credit_account">Credit Account:</form:label>--%>
<%--        <form:input path="credit_account" type="text" class="form-control"/>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <form:errors path="description" class="errors text-danger"/>--%>
<%--        <form:label path="description">Description:</form:label>--%>
<%--        <form:input path="description" type="text" class="form-control"/>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <form:errors path="amount" class="errors text-danger"/>--%>
<%--        <form:label path="amount">Amount:</form:label>--%>
<%--        <form:input path="amount" type="text" class="form-control"/>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <form:errors path="debitAccount" class="errors text-danger"/>--%>
<%--        <form:label path="debitAccount">Debit Account:</form:label>--%>
<%--        <form:select path="" class="form-control">--%>
<%--            <form:options items="${accounts}" itemValue="id" itemLabel="accountNumber"/>--%>
<%--        </form:select>--%>
<%--    </div>--%>
<%--    <form:input type="hidden" path="debitAccount" name="debitAccount.id" value="${newTransaction.debitAccount.id}"/>--%>


<%--    <input class="w-50 btn btn-primary" type="submit" value="Create">--%>


    <label for="debitAccountId">Select Debit Account:</label><br/>
    <select id="debitAccountId" path="debitAccountId" required>
        <option value="">-- Select Debit Account --</option>
        <c:forEach items="${accounts}" var="account">
            <option value="${account.id}">${account.accountNumber} (${account.balance} ${account.currency})</option>
        </c:forEach>
    </select><br/><br/>

    <label for="creditAccountNumber">Credit Account Number:</label><br/>
    <input type="text" id="creditAccountNumber" path="creditAccountNumber" required/><br/><br/>

    <label for="amount">Amount:</label><br/>
    <input type="number" id="amount" path="amount" min="0.01" step="0.01" required/><br/><br/>

    <label for="description">Description:</label><br/>
    <textarea id="description" path="description" rows="4" required></textarea><br/><br/>

    <input type="submit" value="Create Transaction"/>
</form:form>
</body>
</html>
