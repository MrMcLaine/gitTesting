package ua.magazines.shared;

import ua.magazines.entity.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FilterService {
    private static FilterService filterService;

    private FilterService() {
    }

    public static FilterService getFilterService() {
        if (filterService == null) {
            filterService = new FilterService();
        }
        return filterService;
    }

    public void doFilterValidation(ServletRequest request, ServletResponse response, FilterChain chain,
                                   List<Role> userRole) throws IOException, ServletException {

        try {
            HttpSession session = ((HttpServletRequest) request).getSession();
            Role role = Role.valueOf((String) session.getAttribute("role"));

            if (role != null && userRole.contains(role)) {
                chain.doFilter(request, response);
            } else {
                (request).getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            (request).getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}