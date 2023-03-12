package ua.magazines.service;

import ua.magazines.entity.User;

import java.util.ArrayList;
import java.util.List;

public class OldUserService {
    List<User> listOfUsers = new ArrayList<>();
    private static OldUserService userService;

    private OldUserService() {
    }

    public static OldUserService getUserService() {
        if (userService == null) {
            userService = new OldUserService();
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
