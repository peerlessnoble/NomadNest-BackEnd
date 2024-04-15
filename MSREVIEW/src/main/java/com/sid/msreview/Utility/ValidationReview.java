package com.sid.msreview.Utility;

import com.sid.msreview.Dtos.ReviewRequestDto;
import org.modelmapper.spi.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class ValidationReview {
    public static List<ErrorMessage> validate(ReviewRequestDto reviewRequestDto) {
        var errors = new ArrayList<ErrorMessage>();


        if (reviewRequestDto.getHeadline() == null || reviewRequestDto.getHeadline().isEmpty()) {
            errors.add(new ErrorMessage("Headline must not be empty"));
        } else if (reviewRequestDto.getHeadline().length() > 255) {
            errors.add(new ErrorMessage("Headline length must not exceed 255 characters"));
        }
        if (reviewRequestDto.getComment() != null && reviewRequestDto.getComment().length() > 1000) {
            errors.add(new ErrorMessage("Comment length must not exceed 1000 characters"));
        }
        if (reviewRequestDto.getRating() < 1 || reviewRequestDto.getRating() > 5) {
            errors.add(new ErrorMessage("Rating must be between 1 and 5"));
        }
        return errors;
    }
}
