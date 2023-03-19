package ua.magazines.web;

import com.google.gson.Gson;
import ua.magazines.dto.UserLogin;
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

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            User user = userService.getUserByEmail(email);

            if (user != null && user.getPassword().equals(password)) {

                UserLogin userLogin = new UserLogin();
                userLogin.destinationUrl = "cabinet.jsp";
                userLogin.userEmail = user.getEmail();
                String json = new Gson().toJson(userLogin);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("json");

            } else {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

        } catch (NoSuchElementException e) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
