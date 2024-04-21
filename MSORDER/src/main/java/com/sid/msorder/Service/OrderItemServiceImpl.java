package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderItemRequestDto;
import com.sid.msorder.Dtos.OrderItemResponseDto;
import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Exception.OrderItemNotFoundException;
import com.sid.msorder.Exception.OrderNotFoundException;
import com.sid.msorder.Exception.ValidatorException;
import com.sid.msorder.Repository.OrderItemRepository;
import com.sid.msorder.Repository.OrderRepository;
import com.sid.msorder.mappers.MappingProfile;
import com.sid.msorder.utils.ValidationOrderItem;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;


    @Override
    public Page<OrderItemResponseDto> getAllOrderItems(int pageNumber, int pageSize, String field, String order) {
        PageRequest pageRequest=PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC:
                                Sort.Direction.ASC,
                        field)
        );

        return orderItemRepository.findAll(pageRequest).map(MappingProfile ::mapToDto);
    }

    @Override
    public OrderItemResponseDto getOrderItemById(Long orderItemId) throws OrderItemNotFoundException {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("OrderItem not found"));

        return MappingProfile.mapToDto(orderItem);
    }

    @Override
    public OrderItemResponseDto AddOrderItem(OrderItemRequestDto orderItemRequestDTO) {
        List<ErrorMessage> validationErrors = ValidationOrderItem.validate(orderItemRequestDTO);
        if (!validationErrors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessage.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessage.toString());
        }
        Order order = orderRepository.findById(orderItemRequestDTO.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemRequestDTO.getQuantity());
        orderItem.setProductId(orderItemRequestDTO.getProductId());
        orderItem.setOrder(order);

        return MappingProfile.mapToDto(orderItemRepository.save(orderItem));
    }

    @Override
    public OrderItemResponseDto updateOrderItem(Long orderItemId, OrderItemRequestDto orderItemRequestDTO) throws OrderItemNotFoundException {
        List<ErrorMessage> validationErrors = ValidationOrderItem.validate(orderItemRequestDTO);
        if (!validationErrors.isEmpty()) {

            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessage.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessage.toString());
        }

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("Order item not found"));
        orderItem.setQuantity(orderItemRequestDTO.getQuantity());

        return MappingProfile.mapToDto(orderItemRepository.save(orderItem));
    }

    @Override
    public void deleteOrderItem(Long orderItemId) throws OrderItemNotFoundException {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException("Order item not found"));
        orderItemRepository.delete(orderItem);
    }

}

