package ua.lesson5.service;

import ua.lesson5.domain.User;
import ua.lesson5.shared.AbstractCrud;

public interface UserService extends AbstractCrud<User> {
    User getUserByEmail(String email);
}
