package com.sid.msorder.Dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingRequestDto {
    private String shippingAddress;
    private double shippingCost;
    private Long orderId;

}