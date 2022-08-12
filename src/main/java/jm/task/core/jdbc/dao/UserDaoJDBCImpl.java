package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getInstance().getConnection();

    public UserDaoJDBCImpl() {

    }

    private void createDatabaseIfNotExists() {
        String sql = "USE person;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute("CREATE DATABASE IF NOT EXISTS person;");
            preparedStatement.execute("USE person;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        createDatabaseIfNotExists();

        String sql = "CREATE TABLE users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY UNIQUE, " +
                "user_name VARCHAR(255) NOT NULL, " +
                "user_lastname VARCHAR(255) NOT NULL, " +
                "user_age TINYINT NOT NULL);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

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
