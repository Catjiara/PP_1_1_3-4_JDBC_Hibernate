package jm.task.core.jdbc;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (Connection conn = Util.getConnection()) {
            UserService userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("Ivan", "Ivanov", (byte)25);
            userService.saveUser("Petr", "Petrov", (byte)35);
            userService.saveUser("Sidor", "Sidorov", (byte)45);
            userService.saveUser("Fedor", "Fedorov", (byte)55);

            List<User> userList = userService.getAllUsers();
            for (User user : userList) {
                System.out.println(user);
            }

            userService.cleanUsersTable();

            userService.dropUsersTable();

        } catch (ClassNotFoundException x) {
            System.out.println("Возникли проблемы при подключении к базе данных");
        } catch (SQLException x) {
            System.out.println("Возникли проблемы при работе с базой данных");
        }
    }
}
