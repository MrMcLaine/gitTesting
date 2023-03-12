package ua.magazines.dao.impl;

import ua.magazines.dao.UserDao;
import ua.magazines.entity.Role;
import ua.magazines.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.magazines.util.ConnectionUtil.openConnection;

public class UserDaoImpl implements UserDao {
    private static final String READ_ALL = "SELECT * FROM user";
    private static final String CREATE = "INSERT INTO user(first_name, last_name, email, password, role) " +
                                         "VALUES (?, ?, ?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String UPDATE_BY_ID = "UPDATE user SET " +
                                               "first_name = ?, last_name = ?, email = ?, password = ?, " +
                                               "role = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE id = ?";

    private final Connection connection;
    private PreparedStatement preparedStatement;

    public UserDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.connection = openConnection();
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
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User read(Integer id) {
        User user;

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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
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
            e.printStackTrace();
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
            throw new RuntimeException(e);
        }
        return userRecords;
    }
}
