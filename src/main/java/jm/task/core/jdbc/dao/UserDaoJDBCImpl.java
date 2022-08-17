package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    private void createDatabaseIfNotExists() {
        String sql = "CREATE DATABASE IF NOT EXISTS people;";

        Connection connection = Util.getConnection();
        try {
            connection.createStatement().execute(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createUsersTable() {
        createDatabaseIfNotExists();
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY, " +
                "user_name VARCHAR(255) NOT NULL, " +
                "user_lastname VARCHAR(255) NOT NULL, " +
                "user_age TINYINT NOT NULL, " +
                "CHECK (user_age >= 0));";

        Connection connection = Util.getConnection();
        try {
            connection.createStatement().execute("USE people;");
            connection.createStatement().execute(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users;";

        Connection connection = Util.getConnection();
        try {
            connection.createStatement().execute("USE people;");
            connection.createStatement().execute(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (user_name, user_lastname, user_age) VALUES (?, ?, ?);";

        Connection connection = Util.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            connection.createStatement().execute("USE people;");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?;";

        Connection connection = Util.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            connection.createStatement().execute("USE people;");
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users;";

        Connection connection = Util.getConnection();
        try {
            connection.createStatement().execute("USE people;");

            try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
                while (resultSet.next()) {
                    User user = new User(resultSet.getString("user_name"),
                            resultSet.getString("user_lastname"),
                            resultSet.getByte("user_age"));
                    user.setId(resultSet.getLong("id"));
                    usersList.add(user);
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users;";

        Connection connection = Util.getConnection();
        try {
            connection.createStatement().execute("USE people;");
            connection.createStatement().execute(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
