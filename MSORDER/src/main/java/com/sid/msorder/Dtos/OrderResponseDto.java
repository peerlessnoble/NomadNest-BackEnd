package com.sid.msorder.Dtos;

import com.sid.msorder.Enums.OrderStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private Date orderDate;
    private Date updatedDate;
    private OrderStatus orderStatus;
    private List<OrderItemResponseDto> orderItems;
    private ShippingResponseDto shipping;
    //
    //private Double originalPrice;
}

