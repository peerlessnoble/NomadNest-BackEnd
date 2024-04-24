package com.sid.msorder.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "shippings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;
    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_cost")
    private double shippingCost;

    @Column(name = "tracking_number")
    private Long trackingNumber;

    // One-to-one relationship with Order
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;
//    @OneToMany(mappedBy = "shipping", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Order> orders;

    @Column(name = "estimated_delivery_date")
    @Temporal(TemporalType.DATE)
    private LocalDate estimatedDeliveryDate;

    public Shipping(String shippingAddress, double shippingCost, Long trackingNumber, Order order) {
        this.shippingAddress = shippingAddress;
        this.shippingCost = shippingCost;
        this.trackingNumber = trackingNumber;
        this.order = order;
    }

    @PrePersist
    public void generateTrackingNumber() {
        this.trackingNumber = System.currentTimeMillis();
    }

}
