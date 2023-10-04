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
        <th scope="col">Manager</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Assume you have a List<User> userList containing your data
        List<Role> roleList = (List<Role>) request.getAttribute("roleList");
        int i = 1;
        for (Role r : roleList) {
    %>

    <tr>
        <th scope="row"><%= i++ %></th>
        <td><%= r.getRole_name() %></td>
        <td><%= r.getDescription() %></td>
        <td><%= r.getStatus() %></td>
        <td>
            <div class="mb-3 row">
                <div class="col-sm-6">
                    <a href="edit-role?page=editR&action=editRole&roleId=<%= r.getRole_id()%>" class="btn btn-primary">Edit</a>
                </div>

                <div class="col-sm-6 ">
                    <form action="delete-role" method="post">
                        <input type="hidden" name="action" value="delete_role">
                        <input type="hidden" name="roleId" value="<%= r.getRole_id() %>">
                        <input type="submit" value="Delete" class="btn btn-danger"></input>
                    </form>
                </div>

            </div>
        </td>
    </tr>
    <%
        }
    %>

    </tbody>
</table>
