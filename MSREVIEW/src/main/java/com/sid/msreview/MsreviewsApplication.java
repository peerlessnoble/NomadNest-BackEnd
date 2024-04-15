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
            Review review = new Review(null, "Bad", "the quality is bad", 3, new Date(), new Date(), 1L, 1L);
            reviewRepository.saveAll(Arrays.asList(review));
        };
    }

}
