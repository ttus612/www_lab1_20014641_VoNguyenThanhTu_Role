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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
                }else if (pageNav.equals("listAc")) {
                    %><jsp:include page="account/listAccount.jsp" /><%
                }else if (pageNav.equals("infoAc")) {
                    %><jsp:include page="account/informationAccount.jsp" /><%
                } else if (pageNav.equals("listL")) {
                    %><jsp:include page="log/listLog.jsp" /><%
                } else if (pageNav.equals("listR")) {
                    %><jsp:include page="role/listRole.jsp" /><%
                }else if (pageNav.equals("addR")) {
                    %><jsp:include page="role/addRole.jsp" /><%
                }else if (pageNav.equals("listPer")) {
                     %><jsp:include page="account/listRoleAccount.jsp" /><%
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
    <script type="text/javascript">
        function handleSelectChange(selectElement) {
            const selectedValue = selectElement.value
            const url = "list-of-permissions?action=listPermission";
            // window.location.href = "list-of-permissions?action=listPermission"

            $.ajax({
                type: "POST",
                url: url, // Replace with the correct URL to your Servlet
                data: { selectedValue: selectedValue }, // Send selectedValue as a parameter
                success: function (response) {
                    // Handle the response from File 2 (if needed)
                    console.log(response);
                },
                error: function (error) {
                    console.error(error);
                }
            });
        }
    </script>


</body>
</html>
