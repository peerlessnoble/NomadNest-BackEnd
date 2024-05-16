package com.sid.msorder;

import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Entity.Shipping;
import com.sid.msorder.Enums.OrderStatus;
import com.sid.msorder.Repository.OrderItemRepository;
import com.sid.msorder.Repository.OrderRepository;
import com.sid.msorder.Repository.ShippingRepository;
import com.sid.msorder.emails.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@AllArgsConstructor
@SpringBootApplication
@EnableFeignClients(basePackages = "com.nomadnest.clients")
public class MsorderApplication /* implements CommandLineRunner */ {

    public static void main(String[] args) {


        SpringApplication.run(MsorderApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }

}


