package vn.edu.iuh.fit.lab_week01.repositories;

import vn.edu.iuh.fit.lab_week01.db.ConnectDB;
import vn.edu.iuh.fit.lab_week01.models.Logs;
import vn.edu.iuh.fit.lab_week01.models.Role;
import vn.edu.iuh.fit.lab_week01.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
