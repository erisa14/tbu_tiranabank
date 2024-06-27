<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>

<html>
<head>
  <title>Login/Register</title>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
</head>
<body class="mx-auto container-sm" style="background-color: #021c4f">
<div class="mt-5 rounded" style="background: rgb(2,28,79);
background: linear-gradient(180deg, rgba(2,28,79,1) 0%, rgba(244,186,32,1) 100%); width: 40%">
  <div class="d-flex pt-5 justify-content-center gap-2 ">
    <a class="btn text-light btn-lg" style="background-color: #f4ba20"  role="button" id="loginLink">
      Login
    </a>
    <a class="btn btn-lg text-light" style="background-color: #f4ba20" role="button" id="registerLink">
      Register
    </a>
  </div>
    <div class="p-5 collapse rounded"  id="collapseRegister">
      <h3 class="text-light">Register</h3>
      <form:form modelAttribute="newCustomer" action="/register" method="post" class="form gap-3 d-flex flex-column">
        <div class="form-floating d-flex flex-column justify-content-center align-items-center gap-2">
          <form:input path="firstname" id="firstname" type="text" class="form-control" placeholder="Firstname"/>
          <form:label path="firstname" for="firstname">Firstname</form:label>
          <form:errors path="firstname" class="errors text-danger"/>
        </div>
        <div class="form-floating d-flex flex-column justify-content-center align-items-center">
          <form:input path="lastname" id="lastname" type="text" class="form-control" placeholder="Lastname"/>
          <form:label path="lastname" for="lastname">Lastname</form:label>
          <form:errors path="lastname" class="errors text-danger"/>
        </div>
        <div class="form-floating d-flex flex-column justify-content-center align-items-center">
          <form:input path="personalNr" id="personalNr" type="text" class="form-control" placeholder="Personal Number"/>
          <form:label path="personalNr" for="personalNr">Personal Number</form:label>
          <form:errors path="personalNr" class="errors text-danger"/>
        </div>
        <div class=" form-floating d-flex flex-column justify-content-center align-items-center gap-2">
          <form:input path="email" id="email" type="text" class="form-control" placeholder="Email"/>
          <form:label path="email" for="email">Email</form:label>
          <form:errors path="email" class="errors text-danger"/>
        </div>
        <div class="form-floating d-flex flex-column justify-content-center align-items-center gap-2">
          <form:input path="password" id="password" type="password" class="form-control" placeholder="Password"/>
          <form:label path="password" for="password">Password</form:label>
          <form:errors path="password" class="errors text-danger"/>
        </div>
        <div class="form-floating d-flex flex-column align-items-center gap-2">
          <form:input path="confirm" id="confirm" type="password" class="form-control" placeholder="Confirm Password"/>
          <form:label path="confirm" for="confirm">Confirm Password</form:label>
          <form:errors path="confirm" class="errors text-danger"/>
        </div>
        <input class="col-5 btn btn-primary" type="submit" value="Submit">
      </form:form>
    </div>




    <div class="p-5 " id="collapseLogin">
      <h3 class="text-light">Login</h3>
      <form:form modelAttribute="newLogin" action="/login" method="post" class="form gap-3 d-flex flex-column">
        <div class="form-floating d-flex flex-column justify-content-center align-items-center gap-2">
          <form:input path="personalNr" id="personal_nr" type="text" class="form-control" placeholder="Personal Number"/>
          <form:label path="personalNr" for="personal_nr">Personal Number</form:label>
          <form:errors path="personalNr" class="errors text-danger"/>
        </div>
        <div class="form-floating d-flex flex-column justify-content-center align-items-center gap-2">
          <form:input path="password" id="login-pass" type="password" class="form-control" placeholder="Password"/>
          <form:label path="password" for="login-pass">Password</form:label>
          <form:errors path="password" class="errors text-danger"/>
        </div>
        <input class="col-5 btn btn-primary" type="submit" value="Submit">
      </form:form>
    </div>
</div>
  <script>
    document.addEventListener('DOMContentLoaded', function() {
      var loginCollapse = new bootstrap.Collapse(document.getElementById('collapseLogin'), {
        toggle: true
      });

      var registerCollapse = new bootstrap.Collapse(document.getElementById('collapseRegister'), {
        toggle: false
      });

      document.getElementById('loginLink').addEventListener('click', function() {
        loginCollapse.show();
        registerCollapse.hide();
      });

      document.getElementById('registerLink').addEventListener('click', function() {
        registerCollapse.show();
        loginCollapse.hide();
      });
    });
  </script>
</body>
</html>
