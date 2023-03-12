package ua.magazines;

import ua.magazines.entity.User;
import ua.magazines.service.UserService;
import ua.magazines.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        UserService userService = new UserServiceImpl();
        userService.create(new User("Vitaliy", "Kym", "kim@gmail.com", "kimBirthday"));
    }
}
