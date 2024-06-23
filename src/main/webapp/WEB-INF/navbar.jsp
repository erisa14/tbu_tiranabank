<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<nav class="navbar  d-flex px-5">
        <a class="navbar-brand" href="/dashboard">
            <img src="https://www.tiranabank.al/img/tb-color-icon.png" alt="Navbar logo" width="35" height="30">
        </a>
    <div class="d-flex justify-content-end gap-5 space-between pe-5">
        <a class="nav-link" href="/createAccountView">Create Account</a>
        <a class="nav-link" href="/createTransactionView">Create Transaction</a>
        <a class="nav-link" href="/userAccounts">Accounts</a>
        <a class="nav-link" href="/logout">Logout <i class="fa-solid fa-right-from-bracket"></i></a>
    </div>
</nav>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const navLinks = document.querySelectorAll('.nav-link');
        const currentPath = window.location.pathname;

        navLinks.forEach(link => {
            if (link.getAttribute('href') === currentPath) {
                link.classList.add('active');
            }
        });
    });
</script>

<style>
    .nav-link.active{
        color: #f4ba20;
        font-weight: bolder;
        font-size: 22px;
    }
    .nav-link{
        font-size: 20px;
        color: white;
    }
    nav{
        background-color: #021c4f;
    }
</style>