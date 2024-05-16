package com.sid.msorder.Dtos;

import com.nomadnest.clients.Catalog.product.Product;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDto {
    private Long orderItemId;
    private int quantity;
    private Product product;
    private Long orderId;
}

