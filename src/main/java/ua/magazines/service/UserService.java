package ua.magazines.service;

import ua.magazines.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    List<User> listOfUsers = new ArrayList<>();
    private static UserService userService;

    private UserService() {
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public void saveUser(User user) {
        listOfUsers.add(user);
    }

    public User getUser(String email) {
        return listOfUsers.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst().get();
    }
}
