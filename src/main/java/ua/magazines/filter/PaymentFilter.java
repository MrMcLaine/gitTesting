package ua.magazines.filter;

import ua.magazines.entity.Role;
import ua.magazines.shared.FilterService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.List;

@WebFilter("/bucket.jsp")
public class PaymentFilter implements Filter {

    private final FilterService filterService = FilterService.getFilterService();

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        filterService.doFilterValidation(request, response, chain, List.of(Role.USER));
    }

    public void init(FilterConfig fConfig) {
    }
}
