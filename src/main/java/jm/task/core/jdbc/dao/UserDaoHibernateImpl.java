package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private void createDatabaseIfNotExists() {
//        String sql = "USE people";
//        session.createSQLQuery(sql);
    }

    private void useDatabase() {
//        String sql = "CREATE DATABASE IF NOT EXISTS people";
//        session.createSQLQuery(sql);
    }

    @Override
    public void createUsersTable() {
//        createDatabaseIfNotExists();
//        useDatabase();
//
//        String sql = "CREATE TABLE IF NOT EXISTS users (" +
//                "id BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY, " +
//                "user_name VARCHAR(255) NOT NULL, " +
//                "user_lastname VARCHAR(255) NOT NULL, " +
//                "user_age TINYINT NOT NULL, " +
//                "CHECK (user_age >= 0))";
//        session.createSQLQuery(sql);
    }

    @Override
    public void dropUsersTable() {
//        useDatabase();
//
//        String sql = "DROP TABLE IF EXISTS users";
//        session.createSQLQuery(sql);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        session.save(new User(name, lastName, age));
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
//        return session.createQuery("FROM users", User.class).getResultList();
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
