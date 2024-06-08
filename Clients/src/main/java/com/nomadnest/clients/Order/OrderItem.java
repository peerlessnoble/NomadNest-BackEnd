package com.nomadnest.clients.Order;

import lombok.Data;

@Data
public class OrderItem {
    private int quantity;
    private Long productId;
    private Long orderId;
}
