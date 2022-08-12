package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Util {
    private static Util instance = null;
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_NAME = "testdb";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";

    private Util() {
    }

    public static Util getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Util();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR");
        }
        return connection;
    }
}
