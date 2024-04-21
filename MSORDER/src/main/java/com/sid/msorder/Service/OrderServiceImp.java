package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderRequestDto;
import com.sid.msorder.Dtos.OrderResponseDto;
import com.sid.msorder.Entity.Order;
import com.sid.msorder.Enums.OrderStatus;
import com.sid.msorder.Exception.OrderNotFoundException;
import com.sid.msorder.Exception.ValidatorException;
import com.sid.msorder.Repository.OrderRepository;
import com.sid.msorder.emails.EmailService;
import com.sid.msorder.mappers.MappingProfile;
import com.sid.msorder.utils.ValidationOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService{
    private final OrderRepository orderRepository;
    private final EmailService emailService;

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
        return orderRepository.findAll(pageRequest).map(MappingProfile::mapToDto);
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) throws OrderNotFoundException {
        log.info("getOrder By Order" + orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return MappingProfile.mapToDto(order);
    }

    @Override
    public OrderResponseDto AddOrder(OrderRequestDto orderRequestDTO) {
        Order order = MappingProfile.mapToEntity(orderRequestDTO);
        order.setOrderStatus(OrderStatus.CREATED);
        Order savedOrder = orderRepository.save(order);
        return MappingProfile.mapToDto(savedOrder);
    }
    @Override
    public OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDTO) throws OrderNotFoundException {
        List<ErrorMessage> validationErrors = ValidationOrder.validate(orderRequestDTO);
        if (!validationErrors.isEmpty()) {
            StringBuilder errorMessageBuilder = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessageBuilder.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessageBuilder.toString());
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("order not found"));

        OrderStatus previousStatus = order.getOrderStatus();

        order.setOrderStatus(orderRequestDTO.getOrderStatus());
        order.setUpdatedDate(new Date());

        Order savedOrder = orderRepository.save(order);

        if (order.getOrderStatus() != previousStatus) {
            sendOrderStatusUpdateEmail(savedOrder);
        }

        return MappingProfile.mapToDto(savedOrder);
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
