package ua.magazines.service.impl;

import org.apache.log4j.Logger;
import ua.magazines.dao.UserDao;
import ua.magazines.dao.impl.UserDaoImpl;
import ua.magazines.entity.User;
import ua.magazines.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public User create(User user) {
        LOGGER.info("create user with email" + user.getEmail());
        return userDao.create(user);
    }

    @Override
    public User read(Integer id) {
        LOGGER.info("read user with id " + id);
        return userDao.read(id);
    }

    @Override
    public User update(User user) {
        LOGGER.info("update user with email" + user.getEmail());
        return userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("delete user with id " + id);
        userDao.delete(id);
    }

    @Override
    public List<User> readAll() {
        LOGGER.info("read all users");
        return userDao.readAll();
    }
}
