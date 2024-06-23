<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Create Account</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet"></head>
<body  style="background-color: #183c7c" class="mx-auto">
<jsp:include page="navbar.jsp" />

<form:form modelAttribute="newAccount" action="/createAccount" method="post" class="form mt-5 gap-3 d-flex flex-column mx-auto bd-white p-5 border" style="border-color: #f4ba20!important;border-width: 12px!important; width:60%;">
    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
        <form:errors path="account_type" class="errors text-danger"/>
        <form:label path="account_type" class="col-2 fs-5">Account Type:</form:label>
        <form:select class="form-select form-select-lg" path="account_type" id="accountType">
            <form:options items="${accountTypes}" />
        </form:select>
    </div>

    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
        <form:errors path="currency" class="errors text-danger"/>
        <form:label path="currency" class="col-2 fs-5">Currency:</form:label>
        <form:select class="form-select form-select-lg" path="currency" id="accountType">
            <form:options items="${currencies}" />
        </form:select>
    </div>
    <div class="d-flex gap-4 justify-content-center align-items-center gap-2">
        <form:errors path="balance" class="errors text-danger"/>
        <form:label path="balance" class="col-2 fs-5">Balance</form:label>
        <form:input path="balance" type="number" class="form-control form-control-lg"/>
    </div>
    <div class="d-flex justify-content-end">
        <input class="btn-lg col-3 btn btn-primary" type="submit" value="Create Account">
    </div>
</form:form>
<footer>
    <jsp:include page="footer.jsp"/>
</footer>
</body>
</html>
