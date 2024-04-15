package com.sid.msorder.utils;

import com.sid.msorder.Dtos.OrderItemRequestDTO;
import org.modelmapper.spi.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class ValidationOrderItem {
    public static List<ErrorMessage> validate(OrderItemRequestDTO orderItemRequestDTO) {
        var errors = new ArrayList<ErrorMessage>();

        // Check if the orderItemRequestDTO is null
        if (orderItemRequestDTO == null) {
            errors.add(new ErrorMessage("Order item request is null"));
            return errors; // Return here to avoid further validation
        }

        // Validate quantity if not null
        if (orderItemRequestDTO.getQuantity() <= 0) {
            errors.add(new ErrorMessage("Quantity must be greater than 0"));
        }
        // Validate unitPrice if not null
        if (orderItemRequestDTO.getUnitPrice() == 0.0) {
            errors.add(new ErrorMessage("Unit price must be specified"));
        }



        return errors;
    }
}


