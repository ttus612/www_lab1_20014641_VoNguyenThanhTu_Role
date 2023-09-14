<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page import="java.util.List" %><%--
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
    <meta charset="UTF-8">

</head>
<body>
    <div class="container">
        <jsp:include page="navbar.jsp" />
    </div>
    <div class="container">
        <%-- Thêm các thành phần con vào đây --%>
        <%
            String pageNav = request.getParameter("page");
            if (pageNav != null) {
                if (pageNav.equals("home")) {
                    %><jsp:include page="home.jsp" /><%
                } else if (pageNav.equals("layout2")) {
                    %><jsp:include page="layout2.jsp" /><%
                }else if (pageNav.equals("listAc")) {
                    %><jsp:include page="account/listAccount.jsp" /><%
                }else if (pageNav.equals("infoAc")) {
                     %><jsp:include page="account/informationAccount.jsp" /><%
                }

             }
        %>
    </div>
    <!-- Đường dẫn tới các tệp JavaScript của Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
    <script >
        function loadContent(page) {
            $("#content").load(page);
            alert("wef");
        }
    </script>
</body>
</html>
