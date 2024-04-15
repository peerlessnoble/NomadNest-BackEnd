package com.sid.msorder.Dtos;

import com.sid.msorder.Entity.Shipping;
import com.sid.msorder.Enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private List<OrderItemRequestDTO> orderItems;
    private OrderStatus status;
    private Shipping shipping;



}
