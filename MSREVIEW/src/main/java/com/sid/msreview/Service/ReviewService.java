package com.sid.msreview.Service;

import com.sid.msreview.Dtos.ReviewRequestDto;
import com.sid.msreview.Dtos.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto addReviews(ReviewRequestDto reviewRequestDto);
    ReviewResponseDto getReviewById(Long id) throws Exception;
    List<ReviewResponseDto> getAllReviews();
    ReviewResponseDto updateReview(Long id, ReviewRequestDto reviewRequestDto) throws Exception;
    void deleteReview(Long id) throws Exception;

}
