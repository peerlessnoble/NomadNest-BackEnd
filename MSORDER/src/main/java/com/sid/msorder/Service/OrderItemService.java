package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderItemRequestDTO;
import com.sid.msorder.Dtos.OrderItemResponseDTO;
import com.sid.msorder.Dtos.OrderRequestDTO;
import com.sid.msorder.Dtos.OrderResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderItemService {
    Page<OrderItemResponseDTO> getAllOrderItems(int pageNumber,int pageSize, String field,String order);
    OrderItemResponseDTO getOrderItemById(Long id) throws Exception;
    OrderItemResponseDTO AddOrderItem(OrderItemRequestDTO orderItemRequestDTO);
    OrderItemResponseDTO updateOrderItem(Long id,OrderItemRequestDTO orderItemRequestDTO) throws Exception;

     void deleteOrderItem(Long id) throws Exception;
}
