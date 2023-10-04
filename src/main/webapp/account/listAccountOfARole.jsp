<%@ page import="vn.edu.iuh.fit.lab_week01.models.GrantAccess" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.Role" %>

<h1>List account of a role </h1>
<form method="post">
    <label>Fillter Role:</label>
    <br>
    <%--    <select class="form-select" aria-label="Default select example" name="status" onchange="handleSelectChange(this)">--%>
    <select class="form-select" aria-label="Default select example" name="selectedValue">
        <option value=0>Please select account</option>
        <%
            // Assume you have a List<User> userList containing your data
            List<Role> roleList = (List<Role>) request.getAttribute("roleList");
            for (Role r : roleList) {
        %>
            <option value=<%= r.getRole_name()%>><%= r.getRole_name()%></option>
        <%
            }
        %>

    </select>
    <br>
    <input type="hidden" name="action" value="fill_ter">
    <input type="submit" class="btn btn-primary" value="Filter role"></input>

</form>

<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Full Name</th>
        <th scope="col">Email</th>
        <th scope="col">Phone</th>
        <th scope="col">Status Account</th>
        <th scope="col">Role name</th>
        <th scope="col">Description</th>
        <th scope="col">Is grant</th>
        <th scope="col">Note Role</th>
        <th scope="col">Manager</th>
    </tr>
    </thead>
    <tbody id="tableData">

    <%
        List<GrantAccess> grantAccesses = (List<GrantAccess>) request.getAttribute("grantAccessesList");
        int i = 1;
        for (GrantAccess g : grantAccesses) {
    %>

    <tr>
        <th scope="row"><%= i++%></th>
        <td><%= g.getAccount().getFull_name() %></td>
        <td><%= g.getAccount().getEmail() %></td>
        <td><%= g.getAccount().getPhone() %></td>
        <td><%= g.getAccount().getStatus().toString() %></td>
        <td><%= g.getRole().getRole_name() %></td>
        <td><%= g.getRole().getDescription() %></td>
        <td><%= g.getGrant() %></td>
        <td><%= g.getNote() %></td>
        <td>
            <div class="mb-3 row">
                <div class="col-sm-6">
                    <a href="#" class="btn btn-primary">Edit</a>
                </div>

                <div class="col-sm-6">
                    <form action="#" method="post">
                        <input type="hidden" name="action" value="delete_role">
<%--                        <input type="hidden" name="roleId" value="<%= r.getRole_id() %>">--%>
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
