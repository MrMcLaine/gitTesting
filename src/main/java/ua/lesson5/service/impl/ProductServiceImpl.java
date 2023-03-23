package ua.lesson5.service.impl;

import org.apache.log4j.Logger;
import ua.lesson5.dao.ProductDao;
import ua.lesson5.dao.impl.ProductDaoImpl;
import ua.lesson5.domain.Product;
import ua.lesson5.service.ProductService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
    private static ProductService productServiceImpl;

    private final ProductDao productDao;

    private ProductServiceImpl() throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.productDao = new ProductDaoImpl();
    }

    public static ProductService getProductService() throws SQLException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (productServiceImpl == null) {
            productServiceImpl = new ProductServiceImpl();
        }
        return productServiceImpl;
    }

    public Product create(Product product) {
        return productDao.create(product);
    }

    public Product read(Integer id) {
        return productDao.read(id);
    }

    public Product update(Product product) {
        return productDao.update(product);
    }

    public void delete(Integer id) {
        productDao.delete(id);
    }

    public List<Product> readAll() {
        return productDao.readAll();
    }
}
