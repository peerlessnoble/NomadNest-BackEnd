package com.sid.mspayment.Config;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaypalConfig {
    @Value("${client-id}")
    private String clientId; // to get from the PayPal developer dashboard
    @Value("${client-secret}")
    private String clientSecret;
    @Value("${mode}")
    private String mode;

    @Bean
    public APIContext apiContext() {
        return new APIContext(clientId, clientSecret, mode);
    }
}


