package com.sid.mspayment.Controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.sid.mspayment.Service.PaypalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {
    private final PaypalService paypalService;
    @GetMapping("/")
    public String home() {
        return "index";//index=web page

    }
    @PostMapping("/payment/create")
    public RedirectView createPayment(){
        try {
            String cancelUrl="https//localhost:8080/payment/cancel";
            String successUrl="https//localhost:8080/payment/success";
            Payment payment=paypalService.creatPayment(10.0,"USD","paypal","sale","Payment descroption", cancelUrl,successUrl
            );

            for (Links links:payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }

        }catch (PayPalRESTException e) {
            log.error("Error occured::",e);

        }
        return new RedirectView("/payment/error");
    }
/*    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("payerId") String payerId,

    )*/

}
