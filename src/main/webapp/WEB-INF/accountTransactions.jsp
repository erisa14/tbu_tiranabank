<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transactions Between Dates</title>
</head>
<body>
<h2>Transactions Between Dates</h2>

<form action="/account/${account.id}/transactions" method="get">
    <label for="startDate">Start Date:</label>
    <input type="Date" id="startDate" name="startDate" required><br>

    <label for="endDate">End Date:</label>
    <input type="Date" id="endDate" name="endDate" required><br>

    <button type="submit">Submit</button>
</form>



    <table>
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
        <c:forEach items="${transactionList}" var="transaction">
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


</body>
</html>
