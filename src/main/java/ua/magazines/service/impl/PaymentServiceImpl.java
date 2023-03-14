package ua.magazines.service.impl;

import org.apache.log4j.Logger;
import ua.magazines.dao.PaymentDao;
import ua.magazines.dao.impl.PaymentDaoImpl;
import ua.magazines.entity.Payment;
import ua.magazines.service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImpl.class);

    private static PaymentService paymentServiceImpl;

    private final PaymentDao paymentDao;

    private PaymentServiceImpl() {
        this.paymentDao = new PaymentDaoImpl();
    }

    public static PaymentService getPaymentService() {
        if (paymentServiceImpl == null) {
            paymentServiceImpl = new PaymentServiceImpl();
        }
        return paymentServiceImpl;
    }

    @Override
    public Payment create(Payment payment) {
        LOGGER.info("create payment by user with id " + payment.getUserId());
        return paymentDao.create(payment);
    }

    @Override
    public Payment read(Integer id) {
        LOGGER.info("read payment with id " + id);
        return paymentDao.read(id);
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("delete payment with id " + id);
        paymentDao.delete(id);
    }

    @Override
    public List<Payment> readAll() {
        LOGGER.info("read all payments");
        return paymentDao.readAll();
    }
}
