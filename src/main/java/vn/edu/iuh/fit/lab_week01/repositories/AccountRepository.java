package vn.edu.iuh.fit.lab_week01.repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;
import vn.edu.iuh.fit.lab_week01.db.ConnectDB;
import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    //TEST
//    public static void main(String[] args) {
//        AccountRepository accountRepository = new AccountRepository();
//        List<Account> accounts = accountRepository.getAllAccount();
//        for (Account as: accounts) {
//            System.out.println(as);
//        }
//
//        Account account = new Account();
//        account = accountRepository.login("nguyenvanb@gmail.com","123");
//        System.out.printf(String.valueOf(account));
//    }
}
