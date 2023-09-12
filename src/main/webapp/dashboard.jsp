<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 9/12/2023
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

</head>
<body>
    <div class="container">
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
                                <li><a class="dropdown-item" href="#">List account</a></li>
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
    </div>
    <!-- Đường dẫn tới các tệp JavaScript của Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>
