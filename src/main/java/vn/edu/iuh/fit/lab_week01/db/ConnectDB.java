package vn.edu.iuh.fit.lab_week01.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private Connection connection;
    private static ConnectDB instance = null;
    public ConnectDB() throws ClassNotFoundException, SQLException{
        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3306/mydb?createDatabaseIfNotExist=true";
        String user = "root";
        String password = "sapassword";
        connection = DriverManager.getConnection(url, user, password);
    }
    public Connection getConnection() {
        return connection;
    }
    public static ConnectDB getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null){
            instance = new ConnectDB();
        }
        return instance;
    }
    //KIỂM TRA KẾT NỐI
    public boolean isConnected(){
        try {
            return connection != null && !connection.isClosed();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
//    public static void main(String[] args) {
//        try {
//            ConnectDB connectionDB = ConnectDB.getInstance();
//            if (connectionDB.isConnected()){
//                System.out.printf("Sucuess");
//            }else{
//                System.out.printf("Failse");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("Lỗi khi kết nối đến cơ sở dữ liệu.");
//        }
//    }
}
