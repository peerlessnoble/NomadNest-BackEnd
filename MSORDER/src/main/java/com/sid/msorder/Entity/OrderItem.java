package com.sid.msorder.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY) // Ensure proper fetch type
    @JoinColumn(name = "order_id",referencedColumnName="orderId") // Ensure proper join column name
    private Order order; // Proper mapping to Order entity
    public OrderItem(int quantity, Long productId, Order order) {
        this.quantity = quantity;
        this.productId = productId;
        this.order = order;
    }
}
