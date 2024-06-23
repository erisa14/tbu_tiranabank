<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Account Detail</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
</head>
<body  style="background-color: #183c7c">
<jsp:include page="navbar.jsp" />
<h2 class="text-center fs-3 mt-3" style="color:#f4ba20">Details of Account: ${account.accountNumber} </h2>
<table class="table table-striped border w-75 mt-5 mx-auto bd-white p-5" style="border-color: #f4ba20!important; border-width: 12px!important;">
    <thead>
    <tr class="px-4">
        <th>Transaction Date</th>
        <th>Transaction Description</th>
        <th>Transaction Debit</th>
        <th>Transaction Credit</th>
        <th>Transaction Amount</th>
        <th>Balance</th>
        <th>Currency</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${transactionsDebit}" var="transaction">
        <tr class="px-4">
            <td>${transaction.transactionDate.date}/${transaction.transactionDate.month+1}/${transaction.transactionDate.year+1900}</td>
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
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
