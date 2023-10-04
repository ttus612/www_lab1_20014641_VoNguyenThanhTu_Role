package vn.edu.iuh.fit.lab_week01.repositories;

import vn.edu.iuh.fit.lab_week01.db.ConnectDB;
import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.models.Logs;
import vn.edu.iuh.fit.lab_week01.models.Role;
import vn.edu.iuh.fit.lab_week01.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<Role> getAllRole(){
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM role";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                roles.add(new Role(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Status.fromCode(resultSet.getInt("status"))
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return  roles;
    }

    public boolean addRole(Role role) {
        String query = "INSERT INTO role (role_name, description, status) VALUES (?, ?, ?)";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, role.getRole_name());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setInt(3, role.getStatus().getCode());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng tài nguyên (Connection, PreparedStatement, ResultSet) ở đây
            closeResources();
        }
    }
    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteRole(int roleId) {
        String query = "UPDATE role\n" +
                "SET STATUS = -1\n" +
                "WHERE role_id = ?";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roleId);

            int rowsUpdated = preparedStatement.executeUpdate();


            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close resources here
            closeResources();
        }
    }

    public Role getRoleOne(int roleIdString) {
        String query = "SELECT * \n" +
                "FROM role\n" +
                "WHERE  role_id = ?\n";

        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roleIdString);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role(
                resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Status.fromCode(resultSet.getInt("status")));

                return role;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Role getRoleOneName(String roleName) {
        String query = "SELECT * \n" +
                "FROM role\n" +
                "WHERE  role_name = ?\n";

        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roleName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Status.fromCode(resultSet.getInt("status")));

                return role;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean edit(Role role) {

        String query = "UPDATE role SET role_name = ?, DESCRIPTION = ?, STATUS = ? WHERE role_id = ?";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, role.getRole_name());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setInt(3, role.getStatus().getCode());
            preparedStatement.setInt(4, role.getRole_id());
            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println(role);
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources here
            closeResources();
        }

    }
}
