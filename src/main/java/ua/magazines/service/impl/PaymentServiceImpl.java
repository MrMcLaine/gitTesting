package ua.magazines.service.impl;

import ua.magazines.dao.PaymentDao;
import ua.magazines.dao.impl.PaymentDaoImpl;
import ua.magazines.entity.Payment;
import ua.magazines.service.PaymentService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;

    public PaymentServiceImpl() throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.paymentDao = new PaymentDaoImpl();
    }

    @Override
    public Payment create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment read(Integer id) {
        return paymentDao.read(id);
    }

    @Override
    public void delete(Integer id) {
        paymentDao.delete(id);
    }

    @Override
    public List<Payment> readAll() {
        return paymentDao.readAll();
    }
}
