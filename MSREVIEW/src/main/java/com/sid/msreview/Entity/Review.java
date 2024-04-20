package com.sid.msreview.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
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
    @Column(name = "review_creation_date")
    @CreationTimestamp
    private Date creationReviewDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Date updatedReviewDate;

    @Column(name = "review_deleted_date")
    private LocalDateTime deletedDate;


    @Column(name = "product_id")
    private Long product;

    @Column(name = " user_id")
    private Long userId;

    public Review(String headline, String comment, int rating, Date creationReviewDate, Date updatedReviewDate, Long product, Long userId) {
        this.headline = headline;
        this.comment = comment;
        this.rating = rating;
        this.creationReviewDate = creationReviewDate;
        this.updatedReviewDate = updatedReviewDate;
        this.product = product;
        this.userId = userId;
    }

    public Review(String headline, String comment, int rating, Date creationReviewDate, Date updatedReviewDate, LocalDateTime deletedDate, Long product, Long userId) {
        this.headline = headline;
        this.comment = comment;
        this.rating = rating;
        this.creationReviewDate = creationReviewDate;
        this.updatedReviewDate = updatedReviewDate;
        this.deletedDate = deletedDate;
        this.product = product;
        this.userId = userId;
    }
}
