package ua.magazines;

import ua.magazines.entity.User;
import ua.magazines.service.UserService;
import ua.magazines.service.impl.UserServiceImpl;

public class MainTest {
    public static void main(String[] args) {
        UserService userService = UserServiceImpl.getUserService();
        userService.create(new User("Vitaliy", "Kym", "kim@gmail.com", "kimBirthday"));
    }
}
