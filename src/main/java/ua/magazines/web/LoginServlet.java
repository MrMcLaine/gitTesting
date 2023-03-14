package ua.magazines.web;

import ua.magazines.entity.User;
import ua.magazines.service.UserService;
import ua.magazines.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.NoSuchElementException;

public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private final UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = userService.getUserByEmail(login);

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
