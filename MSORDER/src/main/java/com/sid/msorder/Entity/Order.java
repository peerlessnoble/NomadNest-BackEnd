package com.sid.msorder.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sid.msorder.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Enumerated(EnumType.STRING)
    //private PaymentMethod paymentMethod;

    @Column(name = "order_date")
    @CreationTimestamp
    private Date orderDate;

    @Column(name = "updated_date") // Add a new column for updatedDate
    @UpdateTimestamp // Use UpdateTimestamp annotation to automatically update this field
    private Date updatedDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order")
    private Shipping shipping;

    @Column(name = " user_id")
    private Long userId;

   /* @Transient
    @Column( name ="Total_Price")
    private Double totalPrice;*/

   /* public Order(Date orderDate, OrderStatus status, Long userId) {
        this.orderDate = orderDate;
        this.status = status;
        this.userId = userId;


    }*/
    public Order(Date orderDate, OrderStatus status, Long userId,Date updatedDate) {
        this.orderDate = orderDate;
        this.status = status;
        this.userId = userId;
        this.updatedDate=updatedDate;


    }
}

