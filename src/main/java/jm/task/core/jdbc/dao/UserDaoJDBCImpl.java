package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.Util;


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Connection conn = Util.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Users (Id bigint primary key auto_increment, name varchar(100), lastName varchar(100), age tinyint);");
            System.out.println("Создана таблица Users");
        } catch (ClassNotFoundException x) {
            System.out.println("Возникли проблемы c jdbc-драйвером");
        } catch (SQLException x) {
            System.out.println("Возникли проблемы при работе с базой данных");
        }
    }

    public void dropUsersTable() {
        try {
            Connection conn = Util.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS Users;");
            System.out.println("Удалена таблица Users");

        } catch (ClassNotFoundException x) {
            System.out.println("Возникли проблемы c jdbc-драйвером");
        } catch (SQLException x) {
            System.out.println("Возникли проблемы при работе с базой данных");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Connection conn = Util.getConnection();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Users (name, LastName, age) VALUES (?, ?, ?);");
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            int cnt = pst.executeUpdate();
            System.out.println(cnt == 1 ? "User с именем - " + name + " добавлен в базу данных" :
                    "Пользователя " + name + " " + lastName + " не удалось добавить в базу данных");

        } catch (ClassNotFoundException x) {
            System.out.println("Возникли проблемы c jdbc-драйвером");
        } catch (SQLException x) {
            System.out.println("Возникли проблемы при работе с базой данных");
        }
    }

    public void removeUserById(long id) {
        try {
            Connection conn = Util.getConnection();
            Statement st = conn.createStatement();
            int cnt = st.executeUpdate("DELETE FROM Users WHERE Id = " + id + ";");
            System.out.println(cnt == 1 ? "Удален пользоавтель с id = " + id : "В БД не обнаружен пользователь с id = " + id);
        } catch (ClassNotFoundException x) {
            System.out.println("Возникли проблемы c jdbc-драйвером");
        } catch (SQLException x) {
            System.out.println("Возникли проблемы при работе с базой данных");
        }
    }

    public List<User> getAllUsers() {
        List<User> userLst = new ArrayList<>();
        try {
            Connection conn = Util.getConnection();
            Statement st = conn.createStatement();
            ResultSet resSet = st.executeQuery("SELECT name, lastName, age FROM Users;");
            while(resSet.next()) {
                User user = new User(resSet.getString(1), resSet.getString(2), resSet.getByte(3));
                userLst.add(user);
            }
        } catch (ClassNotFoundException x) {
            System.out.println("Возникли проблемы c jdbc-драйвером");
        } catch (SQLException x) {
            System.out.println("Возникли проблемы при работе с базой данных");
        }
        return userLst;
    }

    public void cleanUsersTable() {
        try {
            Connection conn = Util.getConnection();
            Statement st = conn.createStatement();
            int cnt = st.executeUpdate("DELETE FROM Users;");
            System.out.printf("Удалено %s записей из таблицы User\n", cnt);
        } catch (ClassNotFoundException x) {
            System.out.println("Возникли проблемы c jdbc-драйвером");
        } catch (SQLException x) {
            System.out.println("Возникли проблемы при работе с базой данных");
        }
    }
}
