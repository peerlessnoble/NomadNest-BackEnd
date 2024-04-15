package com.sid.msorder.mappers;

import com.sid.msorder.Dtos.*;
import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Entity.Shipping;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


public class MappingProfile {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static Order mapToEntity(OrderRequestDTO orderRequestDTO) {
        return modelMapper.map(orderRequestDTO, Order.class);
    }
    public static OrderResponseDTO mapToDto(Order order) {

        return modelMapper.map(order, OrderResponseDTO.class);
    }

    public static OrderItem mapToEntity(OrderItemRequestDTO orderItemRequestDTO) {
        return modelMapper.map(orderItemRequestDTO, OrderItem.class);
    }
    public static OrderItemResponseDTO mapToDto(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemResponseDTO.class);
    }
    public static Shipping mapToEntity(ShippingRequestDTO shippingRequestDTO) {
        return modelMapper.map(shippingRequestDTO, Shipping.class);
    }
    public static ShippingResponseDTO mapToDto(Shipping shipping) {
        shipping.setEstimatedDeliveryDate(LocalDate.now().plusDays(7));
        return modelMapper.map(shipping, ShippingResponseDTO.class);
    }


}
