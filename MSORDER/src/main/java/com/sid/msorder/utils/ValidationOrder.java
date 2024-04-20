package com.sid.msorder.utils;
import com.sid.msorder.Dtos.OrderRequestDto;
import org.modelmapper.spi.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class ValidationOrder {
    public static List<ErrorMessage> validate(OrderRequestDto orderRequestDTO) {
        var errors = new ArrayList<ErrorMessage>();
        if (orderRequestDTO.getOrderStatus() == null) {
            errors.add(new ErrorMessage("Order status must be specified"));
        }

        return errors;

    }

}
