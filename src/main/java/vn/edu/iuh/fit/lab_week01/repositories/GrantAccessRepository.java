package vn.edu.iuh.fit.lab_week01.repositories;

import vn.edu.iuh.fit.lab_week01.db.ConnectDB;
import vn.edu.iuh.fit.lab_week01.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GrantAccessRepository {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<GrantAccess> getAllGrantAccess(){
        List<GrantAccess> grantAccesses = new ArrayList<>();
        String query = "\n" +
                "SELECT * FROM grant_access G \n" +
                "JOIN role R ON G.role_id = R.role_id\n" +
                "JOIN account A ON A.account_id = G.account_id";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                Role role = new Role(
                        resultSet.getInt("role_id"),
                        resultSet.getString("role_name"),
                        resultSet.getString("description"),
                        Status.fromCode(resultSet.getInt("status"))
                );
                Account account = new Account(
                        resultSet.getString("account_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        Status.fromCode(resultSet.getInt("status"))
                );

                Grant grantAccessId = Grant.fromCode(resultSet.getInt("is_grant"));
                String note = resultSet.getString("note");

                GrantAccess grantAccess = new GrantAccess( role, account, grantAccessId,  note);
                grantAccesses.add(grantAccess);
                System.out.println(grantAccess);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return grantAccesses;
    }

//    public static void main(String[] args) {
//        GrantAccessRepository grantAccessRepository  = new GrantAccessRepository();
//        List<GrantAccess> grantAccesses = grantAccessRepository.getAllGrantAccess();
//        for (GrantAccess g :
//                grantAccesses) {
//            System.out.println(g);
//        }
//    }
}
