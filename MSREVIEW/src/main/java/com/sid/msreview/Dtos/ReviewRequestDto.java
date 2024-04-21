package com.sid.msreview.Dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String headline;
    private String comment;
    private int rating;

    private Long product;


    private Long userId;
}
