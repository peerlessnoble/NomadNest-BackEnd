package com.sid.mspayment.Controller;


import com.nomadnest.clients.Order.OrderServiceClient;
import com.sid.mspayment.Dtos.PaymentRequestDto;
import com.sid.mspayment.Service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/payment")
public class    PaypalController {
    private final PaypalService paypalService;
    private final OrderServiceClient orderServiceClient;
    @GetMapping("/")
    public String home() {
        return "index";//index=web page

    }
    @PostMapping("/create")
    public String createPayment(
            @RequestBody PaymentRequestDto paymentRequestDto
    ){
        try {
            String cancelUrl = "http://localhost:8089/payment/cancel"; // Updated port
            String successUrl = "http://localhost:8089/payment/success"; // Updated port
            System.out.println(paymentRequestDto);
            Payment payment=paypalService.creatPayment(Double.valueOf(paymentRequestDto.getAmount()),
                    paymentRequestDto.getCurrency(),
                    paymentRequestDto.getMethod(),
                    "sale",
                    paymentRequestDto.getDescription(),cancelUrl,successUrl);


            for (Links links:payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    paymentRequestDto.getOrder().setTotalAmount(Double.valueOf(paymentRequestDto.getAmount()));
                    this.orderServiceClient.addOrder(paymentRequestDto.getOrder());
                    return links.getHref();
                }

            }

        }catch (PayPalRESTException e) {
            log.error("Error occured:",e);

        }
        return "/error";

    }
    @GetMapping("/success")
    public RedirectView paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return new RedirectView("http://localhost:4200/shop/confirmation?paymentId=" + paymentId + "&PayerID=" + payerId);
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
        }
        // If payment not approved or error occurred, redirect to error page
        return new RedirectView("http://localhost:4200/payment-error");
    }
    @GetMapping("/cancel")
    public RedirectView paymentCancel() {
        return new RedirectView("http://localhost:4200/payment-cancel");
    }

    @GetMapping("/error")
    public  String paymentError(){
        return "paymentError";
    }



}