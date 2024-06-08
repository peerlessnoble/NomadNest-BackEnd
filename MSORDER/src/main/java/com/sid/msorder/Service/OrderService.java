package com.sid.msorder.Service;


import com.sid.msorder.Dtos.OrderRequestDto;
import com.sid.msorder.Dtos.OrderResponseDto;
import com.sid.msorder.Dtos.StatisticsResponseDto;
import com.sid.msorder.Entity.Order;
import com.sid.msorder.Exception.OrderNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    Page<OrderResponseDto> getAllOrders(int pageNumber, int pageSize, String field, String order);
    OrderResponseDto getOrderById(Long orderId) throws OrderNotFoundException;
    OrderResponseDto AddOrder(OrderRequestDto orderRequestDTO);
    OrderResponseDto updateOrder(Long orderId,OrderRequestDto orderRequestDTO) throws OrderNotFoundException;
    StatisticsResponseDto getStatistics();
    Page<OrderResponseDto> getAllOrdersByUserId(int pageNumber, int pageSize, String field, String order, Long userId);

}

