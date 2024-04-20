package com.sid.msorder.Dtos;

import com.sid.msorder.Enums.OrderStatus;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long customerId;

    private OrderStatus orderStatus;

}


