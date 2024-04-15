package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderRequestDTO;
import com.sid.msorder.Dtos.OrderResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Page<OrderResponseDTO> getAllOrders(int pageNumber,int pageSize, String field,String order);
    OrderResponseDTO getOrderById(Long id) throws Exception;
    OrderResponseDTO AddOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO updateOrder(Long id,OrderRequestDTO orderRequestDTO) throws Exception;

    void deleteOrder(Long id) throws Exception;
}
