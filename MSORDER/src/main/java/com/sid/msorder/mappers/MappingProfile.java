package com.sid.msorder.mappers;

import com.nomadnest.clients.Catalog.product.ProductServiceClient;
import com.nomadnest.clients.User.UserServiceClient;
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

        ProductServiceClient productServiceClient;


        modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemResponseDto>() {
            @Override
            protected void configure() {
                map().setOrderItemId(source.getOrderItemId());
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
                map().setUserId(source.getUserId());

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

    public static OrderResponseDto mapToDto(Order order, UserServiceClient userServiceClient,ProductServiceClient productServiceClient){
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        orderResponseDto.setUser(userServiceClient.getUserById(order.getUserId()));
        orderResponseDto.getOrderItems().forEach(orderItem -> {
            orderItem.setProduct(productServiceClient.getByProductById(orderItem.getProduct().getId()));
        });
        orderResponseDto.setTotal(orderResponseDto.getOrderItems().stream()
                .mapToDouble(orderItem -> orderItem
                        .getProduct().getOriginalPrice() * orderItem.getQuantity()).sum() + order.getShipping().getShippingCost());
        return orderResponseDto;
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