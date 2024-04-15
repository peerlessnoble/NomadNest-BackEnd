package com.sid.msreview.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date ReviewDate;
    private Date updatedReviewDate;
}
