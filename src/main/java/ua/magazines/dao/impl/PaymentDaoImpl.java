package ua.magazines.dao.impl;

import org.apache.log4j.Logger;
import ua.magazines.dao.PaymentDao;
import ua.magazines.entity.Payment;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.magazines.util.ConnectionUtil.openConnection;

public class PaymentDaoImpl implements PaymentDao {

    private static final Logger LOGGER = Logger.getLogger(PaymentDaoImpl.class);
    private static final String READ_ALL = "SELECT * FROM payment";
    private static final String CREATE = "INSERT INTO payment(user_id, magazine_id, date_of_payment, sum_payment) " +
                                         "VALUES (?, ?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM payment WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM payment WHERE id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public PaymentDaoImpl() {
        try {
            this.connection = openConnection();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException | SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Payment create(Payment payment) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, payment.getUserId());
            preparedStatement.setInt(2, payment.getMagazineId());
            preparedStatement.setDate(3, new Date(payment.getDateOfPayment().getTime()));
            preparedStatement.setDouble(4, payment.getSumPayment());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            payment.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return payment;
    }

    @Override
    public Payment read(Integer id) {
        Payment payment = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer paymentId = result.getInt("id");
            Integer userId = result.getInt("user_id");
            Integer magazineId = result.getInt("magazine_id");
            Date dateOfPayment = result.getDate("date_of_payment");
            Double sumPayment = result.getDouble("sum_payment");

            payment = new Payment(paymentId, userId, magazineId, dateOfPayment, sumPayment);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return payment;
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
    public List<Payment> readAll() {
        List<Payment> paymentRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer paymentId = result.getInt("id");
                Integer userId = result.getInt("user_id");
                Integer magazineId = result.getInt("magazine_id");
                Date dateOfPayment = result.getDate("date_of_payment");
                Double sumPayment = result.getDouble("sum_payment");

                paymentRecords.add(new Payment(paymentId, userId, magazineId, dateOfPayment, sumPayment));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return paymentRecords;
    }
}
