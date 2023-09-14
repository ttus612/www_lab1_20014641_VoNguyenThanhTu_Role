<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page import="java.util.List" %>

<h3>List account has register</h3>
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
