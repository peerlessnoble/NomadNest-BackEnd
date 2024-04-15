package com.sid.msreview.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.Date;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String headline;
    private String comment;
    private int rating;
    @Column(name = "order_date")
    @CreationTimestamp
    private Date ReviewDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Date updatedReviewDate;

    // Relationships
    @Column(name = "product_id")
    private Long product;

    @Column(name = " user_id")
    private Long userId;



}
