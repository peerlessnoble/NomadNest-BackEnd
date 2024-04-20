package com.sid.msorder.mappers;

import com.sid.msorder.Dtos.*;
import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Entity.Shipping;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.time.LocalDate;

public class MappingProfile {
    private static final ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);


        modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemResponseDto>() {
            @Override
            protected void configure() {
                map().setOrderItemId(source.getOrderItemId());
                map().setProductId(source.getProductId());
                map().setOrderId(source.getOrder().getOrderId());

            }
        });


        modelMapper.addMappings(new PropertyMap<OrderItemRequestDto, OrderItem>() {
            @Override
            protected void configure() {
                map().setProductId(source.getProductId());

            }
        });


        modelMapper.addMappings(new PropertyMap<OrderRequestDto, Order>() {
            @Override
            protected void configure() {
                // Add mappings for OrderRequestDto to Order
            }
        });


        modelMapper.addMappings(new PropertyMap<OrderItemRequestDto, OrderItem>() {
            @Override
            protected void configure() {

                map().getOrder().setOrderId(source.getOrderId());

            }
        });
        modelMapper.addMappings(new PropertyMap<Shipping, ShippingResponseDto>() {
            @Override
            protected void configure() {
                map().setOrderId(source.getOrder().getOrderId());

            }
        });




    }

    public static Order mapToEntity(OrderRequestDto orderRequestDto){
        return modelMapper.map(orderRequestDto, Order.class);
    }

    public static OrderResponseDto mapToDto(Order order){
        return modelMapper.map(order, OrderResponseDto.class);
    }

    public static OrderItem mapToEntity(OrderItemRequestDto orderItemRequest){
        return modelMapper.map(orderItemRequest, OrderItem.class);
    }

    public static OrderItemResponseDto mapToDto(OrderItem orderItem){
        return modelMapper.map(orderItem, OrderItemResponseDto.class);
    }
    public static Shipping mapToEntity(ShippingRequestDto shippingRequestDTO) {
        return modelMapper.map(shippingRequestDTO, Shipping.class);
    }
    public static ShippingResponseDto mapToDto(Shipping shipping) {
        shipping.setEstimatedDeliveryDate(LocalDate.now().plusDays(7));
        return modelMapper.map(shipping, ShippingResponseDto.class);
    }
}