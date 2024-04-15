package com.sid.msorder;

import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Entity.Shipping;
import com.sid.msorder.Enums.OrderStatus;
import com.sid.msorder.Enums.ShippingStatus;
import com.sid.msorder.Repository.OrderItemRepository;
import com.sid.msorder.Repository.OrderRepository;
import com.sid.msorder.Repository.ShippingRepository;
import com.sid.msorder.emails.EmailService;
import jakarta.ws.rs.core.Application;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@AllArgsConstructor
@SpringBootApplication
public class MsorderApplication /* implements CommandLineRunner */ {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShippingRepository shippingRepository;
    //here
    private final EmailService emailService;

    public static void main(String[] args) {


        SpringApplication.run(MsorderApplication.class, args);
    }
    @Bean

    CommandLineRunner start(){
        return args -> {

            //Order order1=new Order(new Date(), OrderStatus.PENDING,1L);
            Order order1=new Order(new Date(),OrderStatus.PENDING,1L,new Date());
            OrderItem orderItem1 = new OrderItem(null,23,order1,3L);
            OrderItem orderItem2= new OrderItem(null,20,order1,2L);
            OrderItem orderItem3= new OrderItem(null,11,order1,3L);
            //orderItemRepository.saveAll(Arrays.asList(orderItem3,orderItem2,orderItem1));
            Shipping shipping1 = new Shipping(null,"casa",12,"12", LocalDate.now(), ShippingStatus.NOT_SHIPPED,order1);
            Shipping shipping2 = new Shipping(null,"rabat",41,"3",LocalDate.now(), ShippingStatus.LOST,null);
            Shipping shipping3 = new Shipping(null,"tanger",41,"10",LocalDate.now(), ShippingStatus.NOT_SHIPPED,null);
            //shippingRepository.saveAll(Arrays.asList(shipping1,shipping2,shipping3));

            orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2));
            shippingRepository.saveAll(Arrays.asList(shipping1));

        };


    }
    //@EventListener(ApplicationReadyEvent.class)
    //public void sendEmailOnApplicationReady() {
        // Send an email on application startup
    //    emailService.send("beloserlikeme@gmail.com", "Application Started", "Msorder Application has started successfully.");
   // }

    /*
    @Override
    public void run(String... args) throws Exception {
        OrderItem orderItem1 = new OrderItem1(null,23,12.3,1500.99,null,3L);
        OrderItem orderItem2= new OrderItem(null,23,12.3,1500.99,null,3L);
        OrderItem orderItem3= new OrderItem(null,23,12.3,1500.99,null,3L);
        orderItemRepository.saveAll(Arrays.asList(orderItem3,orderItem2,orderItem));

    }

     */


}


