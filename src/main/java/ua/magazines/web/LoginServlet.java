package ua.magazines.web;

import com.google.gson.Gson;
import ua.magazines.dto.UserLogin;
import ua.magazines.entity.User;
import ua.magazines.service.UserService;
import ua.magazines.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;

public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private final UserService userService = UserServiceImpl.getUserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("role", user.getRole().toString());


            UserLogin userLogin = new UserLogin();
            userLogin.destinationUrl = "cabinet.jsp";
            userLogin.userEmail = user.getEmail();

            String json = new Gson().toJson(userLogin);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
