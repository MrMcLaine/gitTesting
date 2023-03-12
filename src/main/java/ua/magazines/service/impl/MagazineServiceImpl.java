package ua.magazines.service.impl;

import ua.magazines.dao.MagazineDao;
import ua.magazines.dao.impl.MagazineDaoImpl;
import ua.magazines.entity.Magazine;
import ua.magazines.service.MagazineService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class MagazineServiceImpl implements MagazineService {

    private final MagazineDao magazineDao;

    public MagazineServiceImpl() throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.magazineDao = new MagazineDaoImpl();
    }

    @Override
    public Magazine create(Magazine magazine) {
        return magazineDao.create(magazine);
    }

    @Override
    public Magazine read(Integer id) {
        return magazineDao.read(id);
    }

    @Override
    public Magazine update(Magazine magazine) {
        return magazineDao.update(magazine);
    }

    @Override
    public void delete(Integer id) {
        magazineDao.delete(id);
    }

    @Override
    public List<Magazine> readAll() {
        return magazineDao.readAll();
    }
}
