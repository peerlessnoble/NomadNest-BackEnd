package com.sid.msorder.Dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {
    private Long orderItemId;
    private int quantity;
    private Long productId;
    private Long orderId;
}

