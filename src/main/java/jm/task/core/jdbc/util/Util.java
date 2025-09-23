package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String DB_URL = "jdbc:mysql://localhost:3306/task_1_1_3";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Kamch25!";

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        System.out.println("Connecting to the database");
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}


