<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Account Detail</title>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Account Number</th>
        <th scope="col">Transaction Date</th>
        <th scope="col">Transaction Description</th>
        <th scope="col">Transaction Debit</th>
        <th scope="col">Transaction Credit</th>
        <th scope="col">Transaction Amount</th>
        <th scope="col">Balance</th>
        <th scope="col">Currency</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${transactionsDebit}" var="transaction">
        <tr>
            <td>${account.accountNumber}</td>
                <td>${transaction.transactionDate}</td>
                <td>${transaction.description}</td>
                <td>${transaction.debitAccount.accountNumber}</td>
                <td>${transaction.creditAccount}</td>
                <td>${transaction.amount}</td>
            <td>${account.balance}</td>
            <td>${account.currency}</td>
        </tr>
    </c:forEach>

    <c:forEach items="${transactionsCredit}" var="transaction">
        <tr>
            <td>${account.accountNumber}</td>
            <td>${transaction.transactionDate}</td>
            <td>${transaction.description}</td>
            <td>${transaction.debitAccount.accountNumber}</td>
            <td>${transaction.creditAccount}</td>
            <td>${transaction.amount}</td>
            <td>${account.balance}</td>
            <td>${account.currency}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>



</body>
</html>
