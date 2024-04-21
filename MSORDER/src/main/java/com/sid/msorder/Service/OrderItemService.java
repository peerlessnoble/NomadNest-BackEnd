package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderItemRequestDto;
import com.sid.msorder.Dtos.OrderItemResponseDto;
import com.sid.msorder.Exception.OrderItemNotFoundException;
import org.springframework.data.domain.Page;


public interface OrderItemService {
    Page<OrderItemResponseDto> getAllOrderItems(int pageNumber, int pageSize, String field, String order);
    OrderItemResponseDto getOrderItemById(Long orderItemId) throws OrderItemNotFoundException;
    OrderItemResponseDto AddOrderItem(OrderItemRequestDto orderItemRequestDTO);
    OrderItemResponseDto updateOrderItem(Long orderItemId, OrderItemRequestDto orderItemRequestDTO) throws OrderItemNotFoundException;

    void deleteOrderItem(Long orderItemId) throws OrderItemNotFoundException;
}
