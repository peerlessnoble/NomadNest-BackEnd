package com.nomadnest.clients.Order;

import lombok.Data;

@Data
public class Shipping {
    private String shippingAddress;
    private double shippingCost;
    private Long orderId;
}
