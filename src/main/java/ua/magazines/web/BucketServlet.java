package ua.magazines.web;

import com.google.gson.Gson;
import ua.magazines.dto.PaymentDto;
import ua.magazines.entity.Magazine;
import ua.magazines.entity.Payment;
import ua.magazines.service.MagazineService;
import ua.magazines.service.PaymentService;
import ua.magazines.service.impl.MagazineServiceImpl;
import ua.magazines.service.impl.PaymentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BucketServlet extends HttpServlet {

    private final PaymentService paymentService = PaymentServiceImpl.getPaymentService();
    private final MagazineService magazineService = MagazineServiceImpl.getMagazineService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Payment> payments = paymentService.readAll();
        Map<Integer, Magazine> idToMagazine = magazineService.readAllMap();
        List<PaymentDto> paymentDtoList = map(payments, idToMagazine);

        String json = new Gson().toJson(paymentDtoList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public List<PaymentDto> map(List<Payment> payments, Map<Integer, Magazine> idToMagazine) {

        return payments.stream().map(payment -> {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.paymentId = payment.getId();
            paymentDto.dateOfPayment = payment.getDateOfPayment();

            Magazine magazine = idToMagazine.get(payment.getMagazineId());
            paymentDto.magazineName = magazine.getName();
            paymentDto.magazineDescription = magazine.getDescription();
            paymentDto.sumPayment = magazine.getPriceForMount();

            return paymentDto;
        }).collect(Collectors.toList());
    }
}
