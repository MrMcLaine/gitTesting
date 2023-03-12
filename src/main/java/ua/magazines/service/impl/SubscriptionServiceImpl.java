package ua.magazines.service.impl;

import ua.magazines.dao.SubscriptionDao;
import ua.magazines.dao.impl.SubscriptionDaoImpl;
import ua.magazines.entity.Subscription;
import ua.magazines.service.SubscriptionService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionDao subscriptionDao;

    public SubscriptionServiceImpl() throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.subscriptionDao = new SubscriptionDaoImpl();
    }

    @Override
    public Subscription create(Subscription subscription) {
        return subscriptionDao.create(subscription);
    }

    @Override
    public Subscription read(Integer id) {
        return subscriptionDao.read(id);
    }

    @Override
    public Subscription update(Subscription subscription) {
        return subscriptionDao.update(subscription);
    }

    @Override
    public void delete(Integer id) {
        subscriptionDao.delete(id);
    }

    @Override
    public List<Subscription> readAll() {
        return subscriptionDao.readAll();
    }
}
