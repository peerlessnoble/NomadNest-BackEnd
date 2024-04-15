package com.sid.msreview.Controller;

import com.sid.msreview.Dtos.ReviewRequestDto;
import com.sid.msreview.Dtos.ReviewResponseDto;
import com.sid.msreview.Entity.Review;
import com.sid.msreview.Service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping("/{id}")
    ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }
    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        List<ReviewResponseDto> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        return ResponseEntity.ok(reviewService.addReviews(reviewRequestDto));
    }

    @PutMapping("/{id}")
    ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long id,@RequestBody ReviewRequestDto reviewRequestDto ) throws Exception{
        return ResponseEntity.ok(reviewService.updateReview(id,reviewRequestDto));
    }
    @DeleteMapping("/{id}")
    void deleteReview(@PathVariable Long id) throws Exception{
        reviewService.deleteReview(id);
    }
}


