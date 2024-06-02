package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main  {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Goborod", (byte) 25);
        userService.saveUser("Nikolay","Baskov", (byte) 22);
        userService.saveUser("Oleg","Makarenko", (byte) 24);
        userService.saveUser("Vasya","DosSantos", (byte) 26);
        userService.saveUser("Maria","Porie", (byte) 28);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

}
