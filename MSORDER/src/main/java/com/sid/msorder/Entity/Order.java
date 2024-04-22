package com.sid.msorder.Entity;


import com.sid.msorder.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(name = "order_date")
    @CreationTimestamp
    private Date orderDate;
    @Column(name = "updated_date")
    @UpdateTimestamp
    private Date updatedDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    // One-to-one relationship with Shipping
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Shipping shipping;

    private Long userId;

    public Order(  OrderStatus orderStatus, List<OrderItem> orderItems, Shipping shipping,Long userId) {
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.shipping = shipping;
        this.userId = userId;
    }
}
