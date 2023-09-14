<%@ page import="vn.edu.iuh.fit.lab_week01.models.Role" %>
<%@ page import="java.util.List" %>
<h1>List role</h1>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Role name</th>
        <th scope="col">Description</th>
        <th scope="col">Status</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Assume you have a List<User> userList containing your data
        List<Role> roleList = (List<Role>) request.getAttribute("roleList");
        for (Role r : roleList) {
    %>

    <tr>
        <th scope="row"><%= r.getRole_id() %></th>
        <td><%= r.getRole_name() %></td>
        <td><%= r.getDescription() %></td>
        <td><%= r.getStatus() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
