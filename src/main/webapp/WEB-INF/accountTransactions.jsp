<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transactions Between Dates</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
</head>
<body   style="background-color: #183c7c">
<jsp:include page="navbar.jsp" />

<h4 class="text-center fs-3 mt-3" style="color:#f4ba20">Transactions of account: ${account.accountNumber}</h4>

<form action="/accountTransactions/${account.id}/dateBetween" method="get" class="form mt-5 gap-3 d-flex flex-column mx-auto bd-white p-5 border" style="border-color: #f4ba20!important;border-width: 12px!important; width:50%;">
    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
    <label for="startDate" class="col-2 fs-5">Start Date:</label>
    <input type="Date" id="startDate" name="startDate" class="form-control" required><br>
    </div>

    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
    <label for="endDate" class="col-2 fs-5">End Date:</label>
    <input type="Date" id="endDate" name="endDate" class="form-control" required><br>
    </div>
    <div class="d-flex justify-content-end">
        <input class="btn-lg col-3 btn btn-primary" type="submit" value="Search">
    </div>
</form>

<c:if test="${not empty transactionDebit or not empty transactionCredit}">
    <h5 class="text-center mt-5" style="color: #f4ba20">Transaction date: ${startDate.date}/${startDate.month+1}/${startDate.year+1900} - ${endDate.date}/${endDate.month+1}/${endDate.year+1900} </h5>
    <table class="table table-striped border w-75 mt-5 mx-auto bd-white p-5" style="border-color: #f4ba20!important;border-width: 12px!important;">
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
                <td>${transaction.transactionDate.date}/${transaction.transactionDate.month+1}/${transaction.transactionDate.year+1900}</td>
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

    <div class="d-flex justify-content-center gap-3">
    <form action="/accountTransactions/${account.id}/download/pdf" method="get">
        <input type="hidden" name="startDate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${startDate}' />">
        <input type="hidden" name="endDate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${endDate}' />">
        <button class="btn-lg btn " type="submit" style="background-color: #f4ba20">Download PDF</button>
    </form>
    <form action="/accountTransactions/${account.id}/download/csv" method="get">
        <input type="hidden" name="startDate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${startDate}' />">
        <input type="hidden" name="endDate" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${endDate}' />">
        <button class="btn-lg  btn " type="submit" style="background-color: #f4ba20">Download CSV</button>
    </form>
    </div>
</c:if>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>


