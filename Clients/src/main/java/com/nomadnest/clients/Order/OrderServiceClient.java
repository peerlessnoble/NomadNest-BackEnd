package com.nomadnest.clients.Order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("MSORDER")
public interface OrderServiceClient {
    @PostMapping("orders/order")
    void addOrder(@RequestBody Order order);
}
