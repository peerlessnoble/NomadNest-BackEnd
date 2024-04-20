package com.sid.msorder.utils;


import com.sid.msorder.Dtos.OrderItemRequestDto;
import org.modelmapper.spi.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class ValidationOrderItem {
    public static List<ErrorMessage> validate(OrderItemRequestDto orderItemRequestDTO) {
        var errors = new ArrayList<ErrorMessage>();

        if (orderItemRequestDTO == null) {
            errors.add(new ErrorMessage("Order item request is null"));
            return errors;
        }

        if (orderItemRequestDTO.getQuantity() <= 0) {
            errors.add(new ErrorMessage("Quantity must be greater than 0"));
        }
        /*if (orderItemRequestDTO.getUnitPrice() == 0.0) {
            errors.add(new ErrorMessage("Unit price must be specified"));
        }*/



        return errors;
    }
}



