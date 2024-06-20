<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transactions Between Dates</title>
</head>
<body>
<h2>Transactions Between Dates</h2>
<h4>Account number: ${account.accountNumber}</h4>

<form action="/accountTransactions/${account.id}/dateBetween" method="get">
    <label for="startDate">Start Date:</label>
    <input type="Date" id="startDate" name="startDate" required><br>

    <label for="endDate">End Date:</label>
    <input type="Date" id="endDate" name="endDate" required><br>

    <button type="submit">Submit</button>
</form>

<c:if test="${not empty transactionDebit or not empty transactionCredit}">
    <table class="table">
        <thead>
        <tr>
            <th>Transaction Date</th>
            <th>Description</th>
            <th>Debit Account</th>
            <th>Credit Account</th>
            <th>Amount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${transactionDebit}" var="transaction">
            <tr>
                <td>${transaction.transactionDate}</td>
                <td>${transaction.description}</td>
                <td>${transaction.debitAccount.accountNumber}</td>
                <td>${transaction.creditAccount}</td>
                <td>${transaction.amount}</td>
            </tr>
        </c:forEach>
        <c:forEach items="${transactionCredit}" var="transaction">
            <tr>
                <td>${transaction.transactionDate}</td>
                <td>${transaction.description}</td>
                <td>${transaction.debitAccount.accountNumber}</td>
                <td>${transaction.creditAccount}</td>
                <td>${transaction.amount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
