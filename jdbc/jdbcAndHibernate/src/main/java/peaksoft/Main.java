package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

public class Main {
    public static void main(String[] args) {
//         реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

//        userService.createUsersTable();
//        userService.dropUsersTable();
//        userService.saveUser("Mirlan", "Arstabek", (byte) 21);
//        userService.removeUserById(1);
//        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
    }
}
