package com.sid.msorder.utils;

import com.sid.msorder.Dtos.ShippingRequestDto;
import org.modelmapper.spi.ErrorMessage;

import java.util.ArrayList;
import java.util.List;


public class ValidationShipping {
    public static List<ErrorMessage> validate(ShippingRequestDto shippingRequestDTO) {
        var errors = new ArrayList<ErrorMessage>();

        if (shippingRequestDTO.getShippingAddress() == null || shippingRequestDTO.getShippingAddress().isEmpty()) {
            errors.add(new ErrorMessage("Shipping address must not be empty"));
        }

        if (shippingRequestDTO.getShippingCost() <= 0) {
            errors.add(new ErrorMessage("Shipping cost must be greater than 0"));
        }

        return errors;
    }
}