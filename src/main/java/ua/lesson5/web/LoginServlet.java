package ua.lesson5.web;

import ua.lesson5.domain.User;
import ua.lesson5.service.UserService;
import ua.lesson5.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = UserServiceImpl.getUserService();

    public LoginServlet() throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
    }

/*    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.getUserByEmail(login);

        try {

            if (user == null) {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

            if (user.getPassword().equals(password)) {
                req.setAttribute("email", login);
                req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

        } catch (NoSuchElementException e) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
