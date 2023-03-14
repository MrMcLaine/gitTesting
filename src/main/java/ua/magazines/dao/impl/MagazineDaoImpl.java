package ua.magazines.dao.impl;

import org.apache.log4j.Logger;
import ua.magazines.dao.MagazineDao;
import ua.magazines.entity.Magazine;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.magazines.util.ConnectionUtil.openConnection;

public class MagazineDaoImpl implements MagazineDao{

    private static final Logger LOGGER = Logger.getLogger(MagazineDaoImpl.class);
    private static final String READ_ALL = "SELECT * FROM magazine";
    private static final String CREATE = "INSERT INTO magazine(name, description, price_for_mount) VALUES (?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM magazine WHERE id = ?";
    private static final String UPDATE_BY_ID = "UPDATE magazine SET " +
                                               "name = ?, description = ?, price_for_mount = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM magazine WHERE id = ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public MagazineDaoImpl() {
        try {
            this.connection = openConnection();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException | SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Magazine create(Magazine magazine) {
        try {
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, magazine.getName());
            preparedStatement.setString(2, magazine.getDescription());
            preparedStatement.setDouble(3, magazine.getPriceForMount());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            magazine.setId(rs.getInt(1));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return magazine;
    }

    @Override
    public Magazine read(Integer id) {
        Magazine magazine = null;

        try {
            preparedStatement = connection.prepareStatement(READ_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Integer magazineId = result.getInt("id");
            String name = result.getString("name");
            String description = result.getString("description");
            Double priceForMount = result.getDouble("price_for_mount");

            magazine = new Magazine(magazineId, name, description, priceForMount);

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return magazine;
    }

    @Override
    public Magazine update(Magazine magazine) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, magazine.getName());
            preparedStatement.setString(2, magazine.getDescription());
            preparedStatement.setDouble(3, magazine.getPriceForMount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return magazine;
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
    public List<Magazine> readAll() {
        List<Magazine> magazineRecords = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer magazineId = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                Double priceForMount = result.getDouble("price_for_mount");

                magazineRecords.add(new Magazine(magazineId, name, description, priceForMount));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return magazineRecords;
    }
}
