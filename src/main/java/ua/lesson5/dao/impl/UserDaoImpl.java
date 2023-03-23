package ua.lesson5.dao.impl;

import org.apache.log4j.Logger;
import ua.lesson5.dao.UserDao;
import ua.lesson5.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.lesson5.util.ConnectionUtil.openConnection;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private static final String READ_ALL = "SELECT * FROM user";
    private static final String CREATE = "INSERT INTO user(email, password, first_name, last_name, role) " +
                                         "VALUES (?, ?, ?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String READ_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String UPDATE_BY_ID = "UPDATE user SET " +
                                               "email = ?, password = ?, first_name = ?, last_name = ?, role = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.connection = openConnection();
    }

    public User create(User user) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    public User read(Integer id) {
        User user = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer userId = result.getInt("id");
            String email = result.getString("email");
            String password = result.getString("password");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String role = result.getString("role");

            user = new User(userId, email, password, firstName, lastName, role);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;
    }

    public User update(User user) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    public List<User> readAll() {
        List<User> userRecords = new ArrayList<User>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer userId = result.getInt("id");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String role = result.getString("role");

                userRecords.add(new User(userId, email, password, firstName, lastName, role));
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
            String password = result.getString("password");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String role = result.getString("role");

            user = new User(userId, email, password, firstName, lastName, role);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return user;
    }
}
