package com.sid.msorder.Service;


import com.sid.msorder.Dtos.OrderRequestDto;
import com.sid.msorder.Dtos.OrderResponseDto;
import com.sid.msorder.Dtos.StatisticsResponseDto;
import com.sid.msorder.Exception.OrderNotFoundException;
import org.springframework.data.domain.Page;

public interface OrderService {
    Page<OrderResponseDto> getAllOrders(int pageNumber, int pageSize, String field, String order);
    OrderResponseDto getOrderById(Long orderId) throws OrderNotFoundException;
    OrderResponseDto AddOrder(OrderRequestDto orderRequestDTO);
    OrderResponseDto updateOrder(Long orderId,OrderRequestDto orderRequestDTO) throws OrderNotFoundException;
    StatisticsResponseDto getStatistics();

}

