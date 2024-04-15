package com.sid.msreview.mappers;

import com.sid.msreview.Dtos.ReviewRequestDto;
import com.sid.msreview.Dtos.ReviewResponseDto;
import com.sid.msreview.Entity.Review;
import org.modelmapper.ModelMapper;

public class MappingProfile {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static Review mapToEntity(ReviewRequestDto reviewRequestDTO) {
        return modelMapper.map(reviewRequestDTO, Review.class);
    }
    public static ReviewResponseDto mapToDto(Review review) {

        return modelMapper.map(review, ReviewResponseDto.class);
    }
}
