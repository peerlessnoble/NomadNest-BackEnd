package com.sid.mspayment.Dtos;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private String method;
    private String amount;
    private String currency;
    private String description;
}
