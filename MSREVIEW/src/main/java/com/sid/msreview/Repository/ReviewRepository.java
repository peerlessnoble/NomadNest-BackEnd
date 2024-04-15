package com.sid.msreview.Repository;

import com.sid.msreview.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findReviewByRating(int rating);
    Optional<Review> findBy(String field,Object value);
}
