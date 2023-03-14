package ua.magazines.service.impl;

import org.apache.log4j.Logger;
import ua.magazines.dao.SubscriptionDao;
import ua.magazines.dao.impl.SubscriptionDaoImpl;
import ua.magazines.entity.Subscription;
import ua.magazines.service.SubscriptionService;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger LOGGER = Logger.getLogger(SubscriptionServiceImpl.class);

    private final SubscriptionDao subscriptionDao;

    public SubscriptionServiceImpl() {
        this.subscriptionDao = new SubscriptionDaoImpl();
    }

    @Override
    public Subscription create(Subscription subscription) {
        LOGGER.info("create subscription for user with id " + subscription.getUserId());
        return subscriptionDao.create(subscription);
    }

    @Override
    public Subscription read(Integer id) {
        LOGGER.info("read subscription with id " + id);
        return subscriptionDao.read(id);
    }

    @Override
    public Subscription update(Subscription subscription) {
        LOGGER.info("update subscription for user with id " + subscription.getUserId());
        return subscriptionDao.update(subscription);
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("delete subscription with id " + id);
        subscriptionDao.delete(id);
    }

    @Override
    public List<Subscription> readAll() {
        LOGGER.info("read all subscriptions");
        return subscriptionDao.readAll();
    }
}
