<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Create Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<form:form modelAttribute="newAccount" action="/createAccount" method="post" class="form gap-3 d-flex flex-column">
    <div>
        <form:errors path="account_type" class="errors text-danger"/>
        <form:label path="account_type">Account Type:</form:label>
        <form:select path="account_type" id="accountType">
            <form:options items="${accountTypes}" />
        </form:select>
    </div>

    <div>
        <form:errors path="currency" class="errors text-danger"/>
        <form:label path="currency">Currency:</form:label>
        <form:select path="currency" id="accountType">
            <form:options items="${currencies}" />
        </form:select>
    </div>
    <div>
        <form:errors path="balance" class="errors text-danger"/>
        <form:label path="balance">Balance</form:label>
        <form:input path="balance" type="number" class="form-control"/>
    </div>
    <input class="w-50 btn btn-primary" type="submit" value="Create">
</form:form>
</body>
</html>
