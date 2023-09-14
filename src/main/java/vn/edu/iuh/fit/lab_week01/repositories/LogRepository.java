package vn.edu.iuh.fit.lab_week01.repositories;

import vn.edu.iuh.fit.lab_week01.db.ConnectDB;
import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.models.Logs;
import vn.edu.iuh.fit.lab_week01.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LogRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<Logs> getAllLogs(){
        List<Logs> logs = new ArrayList<>();
        String query = "SELECT * FROM log";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                logs.add(new Logs(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getTimestamp(3),
                        resultSet.getTimestamp(4),
                        resultSet.getString(5)
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return  logs;
    }

//    public static void main(String[] args) {
//        LogRepository logRepository = new LogRepository();
//        List<Logs> lists = logRepository.getAllLogs();
//        for (Logs l:
//                lists) {
//            System.out.println(String.valueOf(l));
//        }
//    }

}
