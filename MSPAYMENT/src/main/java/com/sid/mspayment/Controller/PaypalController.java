package com.sid.mspayment.Controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.sid.mspayment.Dtos.PaymentRequestDto;
import com.sid.mspayment.Service.PaypalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PaypalController {
    private final PaypalService paypalService;
    @GetMapping("/")
    public String home() {
        return "index";//index=web page

    }
    @PostMapping("/payment/create")
    public String createPayment(
            @RequestBody PaymentRequestDto paymentRequestDto
            ){
        try {
            String cancelUrl="https://localhost:8080/payment/cancel";
            String successUrl="https://localhost:8080/payment/success";
            Payment payment=paypalService.creatPayment(Double.valueOf(paymentRequestDto.getAmount()),
                    paymentRequestDto.getCurrency(),
                    paymentRequestDto.getMethod(),
                    "sale",
                    paymentRequestDto.getDescription(),cancelUrl,successUrl);

            for (Links links:payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return links.getHref();
                }
            }

        }catch (PayPalRESTException e) {
            log.error("Error occured::",e);

        }
        return "/payment/error";

    }
    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId, //automatocally provided by the paypal rest API
            @RequestParam("PayerID") String payerId

    ) {
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return "paymentSuccess";

            }
        }catch (PayPalRESTException e)  {
            log.error("Error occurred:: ", e);
        }
        return "paymentSuccess";
    }
    @GetMapping("/payment/cancel")
    public  String paymentCancel(){
        return "paymentCancel";
    }
    @GetMapping("/payment/error")
    public  String paymentError(){
        return "paymentError";
    }



}
