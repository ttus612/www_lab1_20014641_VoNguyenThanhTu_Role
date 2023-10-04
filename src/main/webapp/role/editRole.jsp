<%@ page import="vn.edu.iuh.fit.lab_week01.models.Role" %><%
    // Assume you have a List<User> userList containing your data
    Role role = (Role) request.getAttribute("role");

%>

<h1>Edit role <%= role.getRole_name()%></h1>
<div class="col-md-12">
    <form action="dash_board" method="post">
        <div class="form-group">
            <label for="rolename">Role name:</label>
            <input type="text" class ="form-control" id="rolename" name="rolename" value="<%= role.getRole_name()%>" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class ="form-control" id="description" name="description" value="<%= role.getDescription()%>" required>
        </div>

        <div class="form-group">
            <label>Status:</label>
            <select class="form-select" aria-label="Default select example"  name="status">
                <option value="0" <%= role.getStatus().getCode() == 0 ? "selected" : "" %>>DEACTIVE</option>
                <option value="1" <%= role.getStatus().getCode() == 1 ? "selected" : "" %>>ACTIVE</option>
                <option value="-1" <%= role.getStatus().getCode() == -1 ? "selected" : "" %>>DELETE</option>
            </select>
        </div>

        <br>
        <input type="hidden" id="role_id" name="role_id" value="<%= role.getRole_id()%>">
        <input type="submit" class="btn btn-primary" value="Edit role"></input>
        <input type="hidden" name="action" value="edit_role">
    </form>
</div>