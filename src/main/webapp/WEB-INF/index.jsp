<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>

<html>
<head>
  <title>Login/Register</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="mx-auto">
<div class="container">
  <h1 class="text-center">User Authentication</h1>
  <div class="d-flex justify-content-center gap-5 mt-3">
    <div class="p-5">
      <h3>Register</h3>
      <form:form modelAttribute="newCustomer" action="/register" method="post" class="form gap-3 d-flex flex-column">
        <div class="d-flex flex-column justify-content-center align-items-center gap-2">
          <form:errors path="firstname" class="errors text-danger"/>
          <form:label path="firstname">Name</form:label>
          <form:input path="firstname" type="text" class="form-control"/>

        </div>
        <div class="d-flex flex-column justify-content-center align-items-center gap-2">
          <form:errors path="lastname" class="errors text-danger"/>
          <form:label path="lastname">Lastname</form:label>
          <form:input path="lastname" type="text" class="form-control"/>
        </div>
        <div class="d-flex flex-column justify-content-center align-items-center gap-2">
          <form:errors path="personalNr" class="errors text-danger"/>
          <form:label path="personalNr">Personal Number</form:label>
          <form:input path="personalNr" type="text" class="form-control"/>
        </div>
        <div class="d-flex flex-column justify-content-center align-items-center gap-2">
          <form:errors path="email" class="errors text-danger"/>
          <form:label path="email">Email</form:label>
          <form:input path="email" type="text" class="form-control"/>
        </div>
        <div class="d-flex flex-column justify-content-center align-items-center gap-2">
          <form:errors path="password" class="errors text-danger"/>
          <form:label path="password">Password</form:label>
          <form:input path="password" type="password" class="form-control"/>
        </div>
        <div class="d-flex flex-column justify-content-center align-items-center gap-2">
          <form:errors path="confirm" class="errors text-danger"/>
          <form:label path="confirm">Confirm Password</form:label>
          <form:input path="confirm" type="password" class="form-control"/>
        </div>
        <input class="w-50 btn btn-primary" type="submit" value="Submit">
      </form:form>
    </div>
    <div class="p-5">
      <h3>Login</h3>
      <form:form modelAttribute="newLogin" action="/login" method="post" class="form gap-3 d-flex flex-column">
        <div class="d-flex flex-column justify-content-center align-items-center gap-2">
          <form:errors path="personalNr" class="errors text-danger"/>
          <form:label path="personalNr">Personal Number</form:label>
          <form:input path="personalNr" type="text" class="form-control"/>
        </div>
        <div class="d-flex flex-column justify-content-center align-items-center gap-2">
          <form:label path="password">Password</form:label>
          <form:input path="password" type="password" class="form-control"/>
          <form:errors path="password" class="errors text-danger"/>
        </div>
        <input class="w-50 btn btn-primary" type="submit" value="Submit">
      </form:form>
    </div>
  </div>
</div>

</div>



</body>
</html>
