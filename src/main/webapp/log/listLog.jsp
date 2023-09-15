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
        <th scope="col">Manager</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Assume you have a List<User> userList containing your data
        List<Logs> logsList = (List<Logs>) request.getAttribute("logList");
        int i = 1;
        for (Logs l : logsList) {
    %>

    <tr>
        <th scope="row"><%= i++ %></th>
        <td><%= l.getAccount_id() %></td>
        <td><%= l.getLogin_time() %></td>
        <td><%= l.getLogout_time() %></td>
        <td><%= l.getNotes() %></td>
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
