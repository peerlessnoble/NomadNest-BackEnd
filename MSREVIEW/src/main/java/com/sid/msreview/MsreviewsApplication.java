package com.sid.msreview;

import com.sid.msreview.Entity.Review;
import com.sid.msreview.Repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@AllArgsConstructor

@SpringBootApplication
public class MsreviewsApplication {
    private final ReviewRepository reviewRepository;

    public static void main(String[] args) {

        SpringApplication.run(MsreviewsApplication.class, args);
    }

    @Bean
    CommandLineRunner start() {
        return args -> {
            Review review1 = new Review(null, "Bad", "the quality is bad", 1, new Date(), new Date(), 1L, 1L);
            Review review2 = new Review(null, "good", "the quality is good", 4, new Date(), new Date(), 1L, 1L);
            Review review3 = new Review(null, "Okay", "the quality is okay", 3, new Date(), new Date(), 1L, 1L);
            reviewRepository.saveAll(Arrays.asList(review1,review2,review3));
        };
    }

}
