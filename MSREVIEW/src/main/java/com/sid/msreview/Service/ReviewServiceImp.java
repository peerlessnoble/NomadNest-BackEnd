package com.sid.msreview.Service;

import com.sid.msreview.Dtos.ReviewRequestDto;
import com.sid.msreview.Dtos.ReviewResponseDto;
import com.sid.msreview.Entity.Review;
import com.sid.msreview.Repository.ReviewRepository;
import com.sid.msreview.mappers.MappingProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImp  implements ReviewService{
    private final ReviewRepository reviewRepository ;

    @Override
    public ReviewResponseDto addReviews(ReviewRequestDto reviewRequestDto) {
        Review review = MappingProfile.mapToEntity(reviewRequestDto);
        return MappingProfile.mapToDto(reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDto getReviewById(Long id) throws Exception {
        Review review =reviewRepository.findById(id).orElseThrow(() -> new Exception("Review not found"));
        return MappingProfile.mapToDto(review);
    }

    @Override
    public List<ReviewResponseDto> getAllReviews() {
        return reviewRepository.findAll().stream().map(MappingProfile :: mapToDto).collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDto updateReview(Long id, ReviewRequestDto reviewRequestDto) throws Exception {
        Review review =reviewRepository.findById(id).orElseThrow(()-> new Exception("Review not found"));
        review.setComment(reviewRequestDto.getComment());
        review.setHeadline(reviewRequestDto.getHeadline());
        review.setRating(reviewRequestDto.getRating());
        return MappingProfile.mapToDto(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(Long id) throws Exception {
        Review review =reviewRepository.findById(id).orElseThrow(()-> new Exception("Review not found"));
        reviewRepository.delete(review);


    }
}
