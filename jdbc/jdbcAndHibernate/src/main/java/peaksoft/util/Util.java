package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String url = "jdbc:postgresql://localhost:5432/jdbc";
    private final static String username = "postgres";
    private final static String password = "mirlan001m.";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
