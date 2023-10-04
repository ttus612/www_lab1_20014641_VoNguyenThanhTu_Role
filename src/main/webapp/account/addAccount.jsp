<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.Role" %><%
    List<Role> roles = (List<Role>) session.getAttribute("roles");
%>
<h1>Add Account</h1>
<div class="col-md-12">

    <form action="dash_board" method="post">
        <div class="form-group">
            <label for="fullName">Full name:</label>
            <input type="text" class ="form-control" id="fullName" name="fullName" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="text" class ="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" class ="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" class ="form-control" id="phone" name="phone" required>
        </div>
        <div class="form-group">
            <label>Status:</label>
            <select class="form-select" aria-label="Default select example" name="selectedValue">
                <option value="0">DEACTIVE</option>
                <option value="1">ACTIVE</option>
                <option value="-1">DELETE</option>
            </select>
        </div>
        <div class="form-group">
            <%--@declare id="roles"--%><label for="roles">Roles:</label><br>
                <%
                    // Assume you have a List<User> userList containing your data

                    for (Role r : roles) {
                %>

            <input type="checkbox" id="<%= r.getRole_name()%>" name="roles[]" value="<%= r.getRole_name()%>">
            <label for="<%= r.getRole_name()%>"><%= r.getRole_name()%></label><br>

            <%
                    }
            %>
        </div>
        <br>
        <input type="submit" class="btn btn-primary" value="Add account"></input>
        <input type="hidden" name="action" value="add_account">
    </form>
</div>