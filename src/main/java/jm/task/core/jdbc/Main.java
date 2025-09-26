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
    }
}
