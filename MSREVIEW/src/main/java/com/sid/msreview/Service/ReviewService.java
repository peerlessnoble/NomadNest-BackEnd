package com.sid.msreview.Service;

import com.sid.msreview.Dtos.ReviewRequestDto;
import com.sid.msreview.Dtos.ReviewResponseDto;
import com.sid.msreview.Exception.ReviewNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto addReviews(ReviewRequestDto reviewRequestDto);
    ReviewResponseDto getReviewById(Long id) throws ReviewNotFoundException;
   Page<ReviewResponseDto> getAllReviews(int pageNumber,int pageSize, String field,String order);
    ReviewResponseDto updateReview(Long id, ReviewRequestDto reviewRequestDto) throws ReviewNotFoundException;
    void deleteReview(Long id) throws ReviewNotFoundException;
    ReviewResponseDto findReviewByRating (int rating) throws ReviewNotFoundException;

}
