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
public class OrderResponseDTO {
    private Long id;

    private List<OrderItemResponseDTO> orderItems;
    private ShippingResponseDTO shipping;
    private Date orderDate;
    private OrderStatus status;
    //private Double total_Price;
    private Date updatedDate;
}
