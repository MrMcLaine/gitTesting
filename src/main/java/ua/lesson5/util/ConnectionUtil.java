package ua.lesson5.util;

import org.apache.log4j.xml.DOMConfigurator;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "password";
    private static final String URL = "jdbc:mysql://localhost/i_shop";

    public static Connection openConnection() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        DOMConfigurator.configure("loggerConfig.xml");
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD);
    }
}
