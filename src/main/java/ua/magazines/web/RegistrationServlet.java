package ua.magazines.web;

import ua.magazines.entity.Role;
import ua.magazines.entity.User;
import ua.magazines.service.UserService;
import ua.magazines.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

public class RegistrationServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private final UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!email.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()) {
            userService.create(new User(firstName, lastName, email, password, Role.USER));
        }

        req.setAttribute("email", email);

        req.getRequestDispatcher("/cabinet.jsp").forward(req, resp);
    }
}
