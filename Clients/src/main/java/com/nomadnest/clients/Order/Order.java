package com.nomadnest.clients.Order;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@Data
public class Order {
    private Long userId;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;
    private Shipping shipping;
    private Long orderId;
    private Double totalAmount;
}
