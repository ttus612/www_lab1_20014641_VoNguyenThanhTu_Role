<%@ page import="vn.edu.iuh.fit.lab_week01.models.Logs" %>
<%@ page import="java.util.List" %>
<h3>List blog</h3>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Account_id</th>
        <th scope="col">Login_time</th>
        <th scope="col">Logout_time</th>
        <th scope="col">Notes</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Assume you have a List<User> userList containing your data
        List<Logs> logsList = (List<Logs>) request.getAttribute("logList");
        for (Logs l : logsList) {
    %>

    <tr>
        <th scope="row"><%= l.getId() %></th>
        <td><%= l.getAccount_id() %></td>
        <td><%= l.getLogin_time() %></td>
        <td><%= l.getLogout_time() %></td>
        <td><%= l.getNotes() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
