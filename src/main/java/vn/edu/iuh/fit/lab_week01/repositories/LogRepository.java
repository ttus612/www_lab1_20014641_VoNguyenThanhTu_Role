package vn.edu.iuh.fit.lab_week01.repositories;

import vn.edu.iuh.fit.lab_week01.db.ConnectDB;
import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.models.Logs;
import vn.edu.iuh.fit.lab_week01.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public boolean getLogLogin(Logs logs){
        String query = "INSERT INTO log (account_id, login_time, logout_time, notes) VALUES (?, ?, ?, ?)";
        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, logs.getAccount_id());
            preparedStatement.setTimestamp(2, logs.getLogin_time());
            preparedStatement.setTimestamp(3, logs.getLogout_time());
            preparedStatement.setString(4, logs.getNotes());

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

    public boolean updateLogTime(Logs logs) throws ParseException {
        String query = "UPDATE log SET logout_time = NOW() WHERE account_id = ? AND login_time = ?";

        String timestampString = String.valueOf(logs.getLogin_time());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = inputFormat.parse(timestampString);

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestampWithoutMillis = outputFormat.format(date);


        try {
            connection = new ConnectDB().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, logs.getAccount_id());
            preparedStatement.setString(2, timestampWithoutMillis);

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
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

//    public static void main(String[] args) {
//        LogRepository logRepository = new LogRepository();
//        List<Logs> lists = logRepository.getAllLogs();
//        for (Logs l:
//                lists) {
//            System.out.println(String.valueOf(l));
//        }
//    }

}
