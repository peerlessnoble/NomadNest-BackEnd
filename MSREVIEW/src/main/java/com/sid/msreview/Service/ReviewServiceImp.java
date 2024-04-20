package com.sid.msreview.Service;

import com.sid.msreview.Dtos.ReviewRequestDto;
import com.sid.msreview.Dtos.ReviewResponseDto;
import com.sid.msreview.Entity.Review;
import com.sid.msreview.Exception.ReviewNotFoundException;
import com.sid.msreview.Exception.ValidatorException;
import com.sid.msreview.Repository.ReviewRepository;
import com.sid.msreview.Utility.ValidationReview;
import com.sid.msreview.mappers.MappingProfile;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImp  implements ReviewService{
    private final ReviewRepository reviewRepository ;

    @Override
    public ReviewResponseDto addReviews(ReviewRequestDto reviewRequestDto) {
        List<ErrorMessage> validationErrors = ValidationReview.validate(reviewRequestDto);
        if (!validationErrors.isEmpty()) {
            StringBuilder errorMessageBuilder = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessageBuilder.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessageBuilder.toString());
        }
        Review review = MappingProfile.mapToEntity(reviewRequestDto);
        return MappingProfile.mapToDto(reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDto getReviewById(Long id) throws ReviewNotFoundException {
        Review review =reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review not found"));
        return MappingProfile.mapToDto(review);
    }

    @Override
    public Page<ReviewResponseDto> getAllReviews(int pageNumber, int pageSize, String field, String order) {
        PageRequest pageRequest=PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC:
                                Sort.Direction.ASC,
                        field)
        );
        return reviewRepository.findAll(pageRequest).map(MappingProfile::mapToDto);
    }

    @Override
    public ReviewResponseDto updateReview(Long id, ReviewRequestDto reviewRequestDto) throws ReviewNotFoundException {

        List<ErrorMessage> validationErrors = ValidationReview.validate(reviewRequestDto);
        if (!validationErrors.isEmpty()) {
            StringBuilder errorMessageBuilder = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessageBuilder.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessageBuilder.toString());
        }

        Review review =reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException("Review not found"));
        review.setComment(reviewRequestDto.getComment());
        review.setHeadline(reviewRequestDto.getHeadline());
        review.setRating(reviewRequestDto.getRating());
        return MappingProfile.mapToDto(reviewRepository.save(review));
    }


    @Override
    public void deleteReview(Long id) throws ReviewNotFoundException {
        Review review =reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException("Review not found"));
        review.setDeletedDate(LocalDateTime.now());
        reviewRepository.save(review);
        reviewRepository.delete(review);

    }

    @Override
    public ReviewResponseDto findReviewByRating(int rating) throws ReviewNotFoundException {
        Review review =reviewRepository.findReviewByRating(rating).orElseThrow(() -> new ReviewNotFoundException("Review not found"));
        return MappingProfile.mapToDto(review);

    }
}
