package com.sid.msorder.Dtos;

import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Entity.Shipping;
import com.sid.msorder.Enums.OrderStatus;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long userId;
    private OrderStatus orderStatus;
    private List<OrderItemRequestDto> orderItems;
    private ShippingRequestDto shipping;
}


