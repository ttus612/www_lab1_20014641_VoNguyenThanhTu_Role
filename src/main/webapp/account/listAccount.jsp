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
        <th scope="col">Manager</th>
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
        <td>
            <div class="mb-3 row">
                <div class="col-sm-6">
                    <a href="#" class="btn btn-primary">Edit</a>
                </div>
                <div class="col-sm-6 ">
                    <form action="delete-account" method="post">
                        <input type="hidden" name="action" value="delete_account">
                        <input type="hidden" name="accountID" value="<%= a.getAccount_id() %>">
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
