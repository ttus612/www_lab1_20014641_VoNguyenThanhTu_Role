<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 9/13/2023
  Time: 1:38 PM
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
          <jsp:include page="../navbar.jsp" />
          <h3>Danh sách các tài khoản đã đăng kí</h3>
          <table class="table table-hover">
              <thead>
              <tr>
                  <th scope="col">#</th>
                  <th scope="col">Full Name</th>
                  <th scope="col">Password</th>
                  <th scope="col">Email</th>
                  <th scope="col">Phone</th>
                  <th scope="col">Status</th>
              </tr>
              </thead>
              <tbody>
              <%
                  // Assume you have a List<User> userList containing your data
                  List<Account> accountList = (List<Account>) request.getAttribute("accountList");
                  for (Account a : accountList) {
              %>

                  <tr>
                      <th scope="row"><%= a.getAccount_id() %></th>
                      <td><%= a.getFull_name() %></td>
                      <td><%= a.getPassword() %></td>
                      <td><%= a.getEmail() %></td>
                      <td><%= a.getPhone() %></td>
                      <td><%= a.getStatus() %></td>
                  </tr>
              <%
                  }
              %>
              </tbody>
          </table>
      </div>

      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

  </body>
</html>
