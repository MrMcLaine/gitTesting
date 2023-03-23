package ua.lesson5.service.impl;

import org.apache.log4j.Logger;
import ua.lesson5.dao.BucketDao;
import ua.lesson5.dao.impl.BucketDaoImpl;
import ua.lesson5.dao.impl.UserDaoImpl;
import ua.lesson5.domain.Bucket;
import ua.lesson5.service.BucketService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class BucketServiceImpl implements BucketService {

    private static final Logger LOGGER = Logger.getLogger(BucketServiceImpl.class);

    private static BucketService bucketServiceImpl;

    private final BucketDao bucketDao;

    private BucketServiceImpl() {
        try {
            this.bucketDao = new BucketDaoImpl();
        } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    public static BucketService getBucketService() {
        if (bucketServiceImpl == null) {
            bucketServiceImpl = new BucketServiceImpl();
        }
        return bucketServiceImpl;
    }

    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    public Bucket read(Integer id) {
        return bucketDao.read(id);
    }

    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    public void delete(Integer id) {
        bucketDao.delete(id);
    }

    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }
}
