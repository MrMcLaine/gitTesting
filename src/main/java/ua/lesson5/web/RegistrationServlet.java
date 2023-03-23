package ua.lesson5.web;

import ua.lesson5.domain.User;
import ua.lesson5.domain.UserRole;
import ua.lesson5.service.UserService;
import ua.lesson5.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = UserServiceImpl.getUserService();

    public RegistrationServlet() throws SQLException, ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        if (!email.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()) {
            userService.create(new User(email, password, firstName, lastName, UserRole.USER.toString()));
        }


        req.setAttribute("email", email);

        HttpSession session = req.getSession();
        session.setAttribute("userEmail", email);
        session.setAttribute("userFirstName", firstName);

        req.getRequestDispatcher("/cabinet.jsp").forward(req, resp);
    }
}
