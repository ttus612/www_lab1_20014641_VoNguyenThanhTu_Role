<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 9/13/2023
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle " href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Account
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Add account</a></li>
                        <li><a class="dropdown-item" href="account/list-account?action=listAccount">List account</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle " href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Log
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Add log</a></li>
                        <li><a class="dropdown-item" href="#">List log</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle " href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Role
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Add role</a></li>
                        <li><a class="dropdown-item" href="#">List role</a></li>
                    </ul>
                </li>

                <li class="nav-item">
                    <a class="nav-link " aria-current="page" href="#">Updating...</a>
                </li>
                <li class="nav-item dropdown d-flex">
                    <a class="nav-link dropdown-toggle " href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        ${session_name}
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#"> Information Account</a></li>
                        <li><a class="dropdown-item" href="#">Log out</a></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</nav>