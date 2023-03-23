package ua.lesson5.dao.impl;

import org.apache.log4j.Logger;
import ua.lesson5.dao.BucketDao;
import ua.lesson5.domain.Bucket;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.lesson5.util.ConnectionUtil.openConnection;

public class BucketDaoImpl implements BucketDao {

    private static final Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);
    private static final String READ_ALL = "SELECT * FROM bucket";
    private static final String CREATE = "INSERT INTO bucket(user_id, product_id, purchase_date) VALUES (?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM bucket WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM bucket WHERE id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public BucketDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.connection = openConnection();
    }

    public Bucket create(Bucket bucket) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, bucket.getUserId());
            preparedStatement.setInt(2, bucket.getProductId());
            preparedStatement.setDate(3, new Date(bucket.getPurchaseDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            bucket.setId(rs.getInt(1));
            throw new SQLException();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucket;
    }

    public Bucket read(Integer id) {
        Bucket bucket = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer bucketId = result.getInt("id");
            Integer userId = result.getInt("user_id");
            Integer productId = result.getInt("product_id");
            java.util.Date purchaseDate = result.getDate("purchase_date");

            bucket = new Bucket(bucketId, userId, productId, purchaseDate);

        } catch (SQLException e) {
            LOGGER.error(e);
        }


        return bucket;
    }

    public Bucket update(Bucket bucket) {
        throw new IllegalStateException("It`s impossible to update bucket");
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

    public List<Bucket> readAll() {
        List<Bucket> bucketRecords = new ArrayList<Bucket>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer bucketId = result.getInt("id");
                Integer userId = result.getInt("user_id");
                Integer productId = result.getInt("product_id");
                java.util.Date purchaseDate = result.getDate("purchase_date");

                bucketRecords.add(new Bucket(bucketId, userId, productId, purchaseDate));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return bucketRecords;
    }
}
