package ua.magazines.service.impl;

import org.apache.log4j.Logger;
import ua.magazines.dao.MagazineDao;
import ua.magazines.dao.impl.MagazineDaoImpl;
import ua.magazines.entity.Magazine;
import ua.magazines.service.MagazineService;

import java.util.List;

public class MagazineServiceImpl implements MagazineService {
    private static final Logger LOGGER = Logger.getLogger(MagazineServiceImpl.class);

    private static MagazineService magazineServiceImpl;

    private final MagazineDao magazineDao;

    private MagazineServiceImpl() {
        this.magazineDao = new MagazineDaoImpl();
    }

    public static MagazineService getMagazineService() {
        if (magazineServiceImpl == null) {
            magazineServiceImpl = new MagazineServiceImpl();
        }
        return magazineServiceImpl;
    }

    @Override
    public Magazine create(Magazine magazine) {
        LOGGER.info("create magazine with name" + magazine.getName());
        return magazineDao.create(magazine);
    }

    @Override
    public Magazine read(Integer id) {
        LOGGER.info("read magazine with id " + id);
        return magazineDao.read(id);
    }

    @Override
    public Magazine update(Magazine magazine) {
        LOGGER.info("update magazine with name " + magazine.getName());
        return magazineDao.update(magazine);
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("delete magazine with id " + id);
        magazineDao.delete(id);
    }

    @Override
    public List<Magazine> readAll() {
        LOGGER.info("read all magazines");
        return magazineDao.readAll();
    }
}
