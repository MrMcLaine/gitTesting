package ua.lesson5.dao.impl;

import ua.lesson5.dao.ProductDao;
import ua.lesson5.domain.Product;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.lesson5.util.ConnectionUtil.openConnection;

public class ProductDaoImpl implements ProductDao {

    private static final String READ_ALL = "SELECT * FROM product";
    private static final String CREATE = "INSERT INTO product(name, description, price) VALUES (?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM product WHERE id = ?";
    private static final String UPDATE_BY_ID = "UPDATE product SET " +
                                               "name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM product WHERE id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductDaoImpl() throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.connection = openConnection();
    }

    public Product create(Product product) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            product.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product read(Integer id) {
        Product product = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer productId = result.getInt("id");
            String name = result.getString("name");
            String description = result.getString("description");
            Double price = result.getDouble("price");

            product = new Product(productId, name, description, price);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return product;
    }

    public Product update(Product product) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public void delete(Integer id) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Product> readAll() {
        List<Product> productRecords = new ArrayList<Product>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer productId = result.getInt("id");
                String name = result.getString("id");
                String description = result.getString("description");
                Double price = result.getDouble("price");

                productRecords.add(new Product(productId, name, description, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productRecords;
    }
}
