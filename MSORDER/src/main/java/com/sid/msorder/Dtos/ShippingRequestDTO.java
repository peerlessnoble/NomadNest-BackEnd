package com.sid.msorder.Dtos;

import com.sid.msorder.Enums.ShippingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingRequestDTO {
    private String shippingAddress;
    private double shippingCost;
    private String trackingNumber;
    private ShippingStatus status;


}
