package com.sid.msorder.Entity;

import com.sid.msorder.Enums.ShippingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "shippings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_cost")
    private double shippingCost;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "estimated_delivery_date")
    @Temporal(TemporalType.DATE)
    private LocalDate estimatedDeliveryDate;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Shipping(String shippingAddress, double shippingCost, String trackingNumber, LocalDate estimatedDeliveryDate, ShippingStatus status, Order order) {
        this.shippingAddress = shippingAddress;
        this.shippingCost = shippingCost;
        this.trackingNumber = trackingNumber;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.status = status;
        this.order = order;
    }
}

