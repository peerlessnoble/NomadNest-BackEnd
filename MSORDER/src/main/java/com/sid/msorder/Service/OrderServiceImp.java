package com.sid.msorder.Service;

import com.nomadnest.clients.Catalog.product.ProductServiceClient;
import com.nomadnest.clients.User.User;
import com.nomadnest.clients.User.UserServiceClient;
import com.sid.msorder.Dtos.OrderItemRequestDto;
import com.sid.msorder.Dtos.OrderRequestDto;
import com.sid.msorder.Dtos.OrderResponseDto;
import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Entity.Shipping;
import com.sid.msorder.Enums.OrderStatus;
import com.sid.msorder.Exception.OrderNotFoundException;
import com.sid.msorder.Exception.ValidatorException;
import com.sid.msorder.Repository.OrderItemRepository;
import com.sid.msorder.Repository.OrderRepository;
import com.sid.msorder.Repository.ShippingRepository;
import com.sid.msorder.emails.EmailService;
import com.sid.msorder.mappers.MappingProfile;
import com.sid.msorder.utils.ValidationOrder;
import com.sid.msorder.utils.ValidationOrderItem;
import com.sid.msorder.utils.ValidationShipping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService{
    private final OrderRepository orderRepository;
    private final EmailService emailService;
    private final UserServiceClient userServiceClient;
    private final OrderItemService orderItemService;
    private final ProductServiceClient productServiceClient;

    @Override
    public Page<OrderResponseDto> getAllOrders(int pageNumber, int pageSize, String field, String order) {
        PageRequest pageRequest=PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC:
                                Sort.Direction.ASC,
                        field)
        );
        return orderRepository.findAll(pageRequest).map(orderm -> MappingProfile.mapToDto(orderm, userServiceClient, productServiceClient));
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) throws OrderNotFoundException {
        log.info("getOrder By Order" + orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return MappingProfile.mapToDto(order, userServiceClient, productServiceClient);
    }

    @Override
    public OrderResponseDto AddOrder(OrderRequestDto orderRequestDTO) {
        List<ErrorMessage> validationErrors = new ArrayList<>();

        for (OrderItemRequestDto orderItem : orderRequestDTO.getOrderItems()) {
            validationErrors.addAll(ValidationOrderItem.validate(orderItem));
        }
        validationErrors.addAll(ValidationShipping.validate(orderRequestDTO.getShipping()));

        if (!validationErrors.isEmpty()) {
            throw new ValidatorException(validationErrors.stream()
                    .map(ErrorMessage::getMessage)
                    .collect(Collectors.joining("; ")));
        }
        Order order = MappingProfile.mapToEntity(orderRequestDTO);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setOrderDate(new Date());
        Shipping shipping = new Shipping();
        shipping.setShippingAddress(orderRequestDTO.getShipping().getShippingAddress());
        shipping.setShippingCost(orderRequestDTO.getShipping().getShippingCost());
        shipping.setOrder(order);
        shipping.setEstimatedDeliveryDate(LocalDate.now().plusDays(7));
        order.setShipping(shipping);
        order.getOrderItems().forEach(orderItem -> {
            orderItem.setOrder(order);
        });
        order.getShipping().setOrder(order);
        Order savedOrder = orderRepository.save(order);


        return MappingProfile.mapToDto(savedOrder, userServiceClient, productServiceClient);
    }





    @Override
    public OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDTO) throws OrderNotFoundException {
        List<ErrorMessage> validationErrors = new ArrayList<>();
        validationErrors.addAll(ValidationOrder.validate(orderRequestDTO));
        orderRequestDTO.getOrderItems().forEach(orderItemRequestDto ->
                validationErrors.addAll(ValidationOrderItem.validate(orderItemRequestDto)));

        validationErrors.addAll(ValidationShipping.validate(orderRequestDTO.getShipping()));
        if (!validationErrors.isEmpty()) {
            throw new ValidatorException(validationErrors.stream()
                    .map(ErrorMessage::getMessage)
                    .collect(Collectors.joining("; ")));
        }
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        OrderStatus previousStatus = order.getOrderStatus();
        order.setOrderStatus(orderRequestDTO.getOrderStatus());
        order.setUpdatedDate(new Date());
        Order savedOrder = orderRepository.save(order);
        if (order.getOrderStatus() != previousStatus) {
            sendOrderStatusUpdateEmail(savedOrder);
        }
        return MappingProfile.mapToDto(savedOrder, userServiceClient, productServiceClient);
    }


    // Method to send email
    private void sendOrderStatusUpdateEmail(Order order) {
        String recipientEmail = "beloserlikeme@gmail.com";
        //String recipientEmail = order.getCustomerEmail();
        String emailSubject = "";
        String emailContent = "";

        switch (order.getOrderStatus()) {
            case PROCESSING:
                emailSubject = "Your Order Has Been Processed";
                emailContent = "Dear Customer,\n\nYour order (ID: " + order.getOrderId() + ") has been processed and is now being prepared for shipping.";
                break;
            case CANCELED:
                emailSubject = "Order Cancellation Confirmation";
                emailContent = "Dear Customer,\n\nWe regret to inform you that your order (ID: " + order.getOrderId() + ") has been cancelled.";
                break;
            case DELIVERED:
                emailSubject = "Your Order Has Been Delivered";
                emailContent = "Dear Customer,\n\nWe are pleased to inform you that your order (ID: " + order.getOrderId() + ") has been delivered successfully.";
                break;
            default:

                break;
        }
        emailService.send(recipientEmail, emailSubject, emailContent);
    }


}