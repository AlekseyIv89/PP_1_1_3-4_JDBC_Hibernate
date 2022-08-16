package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";

//    private static final SessionFactory sessionFactory;

    static {
        loadDriverJDBC();
    }

//    static {
//        Configuration configuration = new Configuration();
//        configuration.setProperty("hibernate.connection.driver_class", DB_DRIVER);
//        configuration.setProperty("hibernate.connection.url", DB_URL);
//        configuration.setProperty("hibernate.connection.username", DB_USERNAME);
//        configuration.setProperty("hibernate.connection.password", DB_PASSWORD);
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        configuration.setProperty("hibernate.show_sql", "true");
//
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties());
//        sessionFactory = configuration.addAnnotatedClass(User.class).buildSessionFactory(builder.build());
//    }

    private static void loadDriverJDBC() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
}
