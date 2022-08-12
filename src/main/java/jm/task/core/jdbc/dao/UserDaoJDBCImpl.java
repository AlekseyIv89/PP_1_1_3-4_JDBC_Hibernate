package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection CONNECTION = Util.getInstance().getConnection();

    public UserDaoJDBCImpl() {
    }

    private void useDatabase() {
        String sql = "USE people;";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDatabaseIfNotExists() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute("CREATE DATABASE IF NOT EXISTS people;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        createDatabaseIfNotExists();
        useDatabase();

        try (Statement statement = CONNECTION.createStatement()) {
            String sql = "CREATE TABLE users (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE, " +
                    "user_name VARCHAR(255) NOT NULL, " +
                    "user_lastname VARCHAR(255) NOT NULL, " +
                    "user_age TINYINT NOT NULL);";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        useDatabase();

        String sql = "DROP TABLE IF EXISTS users";

        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
