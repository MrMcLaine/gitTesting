package ua.lesson5.dao;

import ua.lesson5.domain.User;
import ua.lesson5.shared.AbstractCrud;

public interface UserDao extends AbstractCrud<User> {
    User getUserByEmail(String email);
}
