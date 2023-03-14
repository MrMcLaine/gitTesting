package ua.magazines.dao.impl;

import org.apache.log4j.Logger;
import ua.magazines.dao.UserDao;
import ua.magazines.entity.Role;
import ua.magazines.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.magazines.util.ConnectionUtil.openConnection;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private static final String READ_ALL = "SELECT * FROM user";
    private static final String CREATE = "INSERT INTO user(first_name, last_name, email, password, role) " +
                                         "VALUES (?, ?, ?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String READ_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String UPDATE_BY_ID = "UPDATE user SET " +
                                               "first_name = ?, last_name = ?, email = ?, password = ?, " +
                                               "role = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDaoImpl() {
        try {
            this.connection = openConnection();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException | SQLException e) {
            LOGGER.error(e);
        }
    }


    @Override
    public User create(User user) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer userId = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String email = result.getString("email");
            String password = result.getString("password");
            Role role = Role.valueOf(result.getString("role"));

            user = new User(userId, firstName, lastName, email, password, role);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;
    }

    @Override
    public User update(User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<User> readAll() {
        List<User> userRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer userId = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String email = result.getString("email");
                String password = result.getString("password");
                Role role = Role.valueOf(result.getString("role"));

                userRecords.add(new User(userId, firstName, lastName, email, password, role));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return userRecords;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer userId = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String password = result.getString("password");
            Role role = Role.valueOf(result.getString("role"));

            user = new User(userId, firstName, lastName, email, password, role);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }
}
