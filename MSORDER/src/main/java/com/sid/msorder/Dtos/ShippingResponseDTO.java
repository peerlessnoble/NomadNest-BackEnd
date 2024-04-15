package com.sid.msorder.Dtos;

import com.sid.msorder.Enums.ShippingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingResponseDTO {
    private Long id;
    private String shippingAddress;
    private double shippingCost;
    private String trackingNumber;
    private LocalDate estimatedDeliveryDate;
    private ShippingStatus status;
}
