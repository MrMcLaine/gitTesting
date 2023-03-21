package ua.magazines.web;

import com.google.gson.Gson;
import ua.magazines.entity.Magazine;
import ua.magazines.service.MagazineService;
import ua.magazines.service.impl.MagazineServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomePageServlet extends HttpServlet {
    private final MagazineService magazineService = MagazineServiceImpl.getMagazineService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Magazine> magazines = magazineService.readAll();
        String json = new Gson().toJson(magazines);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
