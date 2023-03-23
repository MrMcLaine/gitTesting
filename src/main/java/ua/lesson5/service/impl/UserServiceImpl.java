package ua.lesson5.service.impl;

import ua.lesson5.dao.UserDao;
import ua.lesson5.dao.impl.UserDaoImpl;
import ua.lesson5.domain.User;
import ua.lesson5.service.UserService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserService userServiceImpl;

    private final UserDao userDao;

    private UserServiceImpl() throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.userDao = new UserDaoImpl();
    }

    public static UserService getUserService() throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
            if (userServiceImpl == null) {
               userServiceImpl = new UserServiceImpl();
            }
        return userServiceImpl;
    }

    public User create(User user) {
        return userDao.create(user);
    }

    public User read(Integer id) {
        return userDao.read(id);
    }

    public User update(User user) {
        return userDao.update(user);
    }

    public void delete(Integer id) {
        userDao.delete(id);
    }

    public List<User> readAll() {
        return userDao.readAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
}
