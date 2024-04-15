package com.sid.msorder.Entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    //@Column(name = "unit_price")
   // private double unitPrice;
    /*@Transient
    private Double totalPrice;*/


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_id")
    private Long productId;


   /*public double getTotalPrice() {
        return unitPrice * quantity;
    }*/

    public OrderItem(int quantity, Order order, Long productId) {
        this.quantity = quantity;
        //this.unitPrice = unitPrice;
        this.order = order;
        this.productId = productId;
    }
}


