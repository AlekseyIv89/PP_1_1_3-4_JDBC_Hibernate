package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
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
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY, " +
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
        String sql = "INSERT INTO users (user_name, user_lastname, user_age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Statement statement = CONNECTION.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("user_name"));
                user.setLastName(resultSet.getString("user_lastname"));
                user.setAge(resultSet.getByte("user_age"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
