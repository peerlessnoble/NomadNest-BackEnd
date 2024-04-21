package com.sid.msorder.Dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingResponseDto {
    private Long shippingId;
    private String shippingAddress;
    private double shippingCost;
    private Long trackingNumber;
    private LocalDate estimatedDeliveryDate;
    private Long orderId;
}
