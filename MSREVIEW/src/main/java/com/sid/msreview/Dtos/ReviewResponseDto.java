package com.sid.msreview.Dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private String headline;
    private String comment;
    private int rating;
    private Date updatedReviewDate;
    private Date creationReviewDate;
    private Long product;
    private Long userId;
}
