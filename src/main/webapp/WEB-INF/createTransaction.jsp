<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Create Transaction</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet"></head>
<body  style="background-color: #183c7c;" class="mx-auto">
<jsp:include page="navbar.jsp" />


<c:if test="${not empty transactionError}">
    <p style="color: red;">${transactionError}</p>
</c:if>
<form:form modelAttribute="newTransaction" action="/createTransaction" method="post" class="form gap-3 d-flex flex-column mx-auto mt-5 bd-white p-5 border rounded" style="border-color: #f4ba20!important;border-width: 12px!important; width:60%;">
    <c:if test="${!empty errors}">
        <ul style="color: red;">
            <c:forEach items="${errors.allErrors}" var="error">
                <li><c:out value="${error.defaultMessage}" /></li>
            </c:forEach>
        </ul>
    </c:if>
    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
        <form:errors path="creditAccount" class="errors text-danger"/>
        <form:label path="creditAccount" class="col-2 fs-5">Credit Account:</form:label>
        <form:input path="creditAccount" type="text" class="form-control form-control-lg"/>
    </div>
    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
        <form:errors path="description" class="errors text-danger"/>
        <form:label path="description" class="col-2 fs-5">Description:</form:label>
        <form:textarea path="description" type="text" class="form-control"/>
    </div>
    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
        <form:errors path="amount" class="errors text-danger"/>
        <form:label path="amount" class="col-2 fs-5">Amount:</form:label>
        <form:input path="amount" type="text" class="form-control form-control-lg"/>
    </div>
    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
        <form:errors path="debitAccount" class="errors text-danger"/>
        <form:label path="debitAccount" class="col-2 fs-5">Debit Account:</form:label>
        <form:select path="debitAccount" class="form-select form-select-lg">
            <c:forEach items="${accounts}" var="account">
                <option value="${account.id}" >${account.accountNumber}</option>
        </c:forEach>
        </form:select>
    </div>


    <div class="d-flex justify-content-end">
        <input class="btn-lg col-3 btn btn-primary" type="submit" value="Create Transaction">
    </div>

</form:form>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
