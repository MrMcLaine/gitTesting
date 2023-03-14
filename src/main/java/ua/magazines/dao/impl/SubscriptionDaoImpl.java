package ua.magazines.dao.impl;

import org.apache.log4j.Logger;
import ua.magazines.dao.SubscriptionDao;
import ua.magazines.entity.Subscription;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.magazines.util.ConnectionUtil.openConnection;

public class SubscriptionDaoImpl implements SubscriptionDao {

    private static final Logger LOGGER = Logger.getLogger(SubscriptionDaoImpl.class);
    private static final String READ_ALL = "SELECT * FROM subscription";
    private static final String CREATE = "INSERT INTO subscription(user_id, magazine_id, status) VALUES (?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM subscription WHERE id = ?";
    private static final String UPDATE_BY_ID = "UPDATE subscription SET " +
                                               "user_id = ?, magazine_id = ?, status = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM subscription WHERE id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public SubscriptionDaoImpl() {
        try {
            this.connection = openConnection();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException | SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Subscription create(Subscription subscription) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, subscription.getUserId());
            preparedStatement.setInt(2, subscription.getMagazineId());
            preparedStatement.setBoolean(3, subscription.getStatus());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            subscription.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return subscription;
    }

    @Override
    public Subscription read(Integer id) {
        Subscription subscription = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer subscriptionId = result.getInt("id");
            Integer userId = result.getInt("user_id");
            Integer magazineId = result.getInt("magazine_id");
            Boolean status = result.getBoolean("status");

            subscription = new Subscription(subscriptionId, userId, magazineId, status);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return subscription;
    }

    @Override
    public Subscription update(Subscription subscription) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setInt(1, subscription.getUserId());
            preparedStatement.setInt(2, subscription.getMagazineId());
            preparedStatement.setBoolean(3, subscription.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return subscription;
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
    public List<Subscription> readAll() {
        List<Subscription> subscriptionRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer subscriptionId = result.getInt("id");
                Integer userId = result.getInt("user_id");
                Integer magazineId = result.getInt("magazine_id");
                Boolean status = result.getBoolean("status");

                subscriptionRecords.add(new Subscription(subscriptionId, userId, magazineId, status));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return subscriptionRecords;
    }
}
