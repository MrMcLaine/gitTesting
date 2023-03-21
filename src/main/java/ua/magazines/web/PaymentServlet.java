package ua.magazines.web;

import ua.magazines.entity.Payment;
import ua.magazines.service.PaymentService;
import ua.magazines.service.impl.PaymentServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class PaymentServlet extends HttpServlet {

    private final PaymentService paymentService = PaymentServiceImpl.getPaymentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String magazineId = request.getParameter("magazineId");
        String price = request.getParameter("price");

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        Payment payment = new Payment(userId, Integer.parseInt(magazineId), new Date(), Double.parseDouble(price));
        paymentService.create(payment);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Success");
    }
}
