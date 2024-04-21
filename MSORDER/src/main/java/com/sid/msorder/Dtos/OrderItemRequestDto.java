package com.sid.msorder.Dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDto {

    private int quantity;
    private Long productId;
    private Long orderId;
}
