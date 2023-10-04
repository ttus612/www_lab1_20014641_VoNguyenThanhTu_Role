package vn.edu.iuh.fit.lab_week01.repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.lab_week01.db.ConnectDB;
import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.models.Role;
import vn.edu.iuh.fit.lab_week01.models.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<Account> getAllAccount(){
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM account";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                accounts.add(new Account(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        Status.fromCode(resultSet.getInt("status"))
                        ));
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return accounts;
    }

    public Account login(String email, String password){
        String query = "SELECT * FROM account WHERE EMAIL = ? AND PASSWORD = ?";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        Status.fromCode(resultSet.getInt("status"))
                );

                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean checkAdmin(Account account){
        String query = "SELECT * FROM account A\n" +
                "JOIN grant_access G ON A.account_id = G.account_id\n" +
                "JOIN role R ON G.role_id = R.role_id\n" +
                "WHERE email = ? AND PASSWORD = ? AND A.STATUS = 1 AND R.role_name = 'admin'";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            resultSet = preparedStatement.executeQuery();

            int checkAdmin = 0;
            if (resultSet.next()){
                 checkAdmin = 1;
            }
            return checkAdmin > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
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

    public Boolean deleteAccount(String accountIdString) {
        String query = "UPDATE account\n" +
                "SET STATUS = -1\n" +
                "WHERE account_id = ?";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountIdString);

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

    public boolean addAccount(String fullName, String password, String email, String phone, String selected) throws SQLException {
        String query = "INSERT INTO account (account_id, full_name, password, email, phone, status) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = new ConnectDB().getConnection();
            String id = getNextId(connection);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);
            preparedStatement.setInt(6, Integer.parseInt(selected));

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

    private String getNextId(Connection connection) throws SQLException {
        // Lấy ID sau cùng từ cơ sở dữ liệu
        String query = "SELECT MAX(account_id) FROM account";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                String currentMaxId = resultSet.getString(1);

                // Nếu không có ID nào trong bảng, trả về một giá trị mặc định
                if (currentMaxId == null) {
                    return "1";
                }

                // Tăng giá trị ID lớn nhất hiện tại thêm 1
                int nextId = Integer.parseInt(currentMaxId) + 1;
                return String.valueOf(nextId);
            } else {
                // Trong trường hợp không có kết quả từ truy vấn, trả về giá trị mặc định
                return "1";
            }
        }
    }




    public Account findAccount(String fullName, String password, String email, String phone, String selected) {
        String query = "SELECT * FROM account WHERE FULL_NAME = ? AND PASSWORD = ? AND EMAIL = ? AND PHONE = ? AND STATUS = ?";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, Integer.parseInt(selected));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        Status.fromCode(resultSet.getInt("status"))
                );

                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean addGrant(Account account, Role role1) {
        String query = "INSERT INTO grant_access (role_id, account_id, is_grant, note) VALUES (?, ?, ?, ?)";
        int is_grant = 1;
        String note = "NHAN VIEN "+ account.getFull_name()+" VOI CHUC VU "+ role1.getRole_name();
        try {
            connection = new ConnectDB().getConnection();
            String id = getNextId(connection);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, role1.getRole_id());
            preparedStatement.setString(2, account.getAccount_id());
            preparedStatement.setInt(3, is_grant);
            preparedStatement.setString(4, note);
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
}
