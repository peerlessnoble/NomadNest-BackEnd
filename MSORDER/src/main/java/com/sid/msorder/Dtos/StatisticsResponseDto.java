package com.sid.msorder.Dtos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponseDto {
    private Integer totalUsers;
    private Integer totalOrders;
    private Integer totalProducts;
    private Double totalEarnings;

}
