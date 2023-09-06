package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String HOST = "localhost";
    private static final int PORT = 3306 ;
    private static final String DB_NAME = "library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public static  Connection dbConnection(){
        //jdbc:mysql://localhost:3306/library
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD);
            System.out.println("done");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection ;
    }
}