package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderRequestDTO;
import com.sid.msorder.Dtos.OrderResponseDTO;
import com.sid.msorder.Dtos.ShippingRequestDTO;
import com.sid.msorder.Dtos.ShippingResponseDTO;
import com.sid.msorder.Entity.Shipping;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShippingService {
    Page<ShippingResponseDTO > getAllShipping(int pageNumber, int pageSize, String field, String order);
    ShippingResponseDTO getShippingById(Long id) throws Exception;
    ShippingResponseDTO  AddShipping(ShippingRequestDTO shippingRequestDTO);
    ShippingResponseDTO updateShipping(Long id, ShippingRequestDTO shippingRequestDTO) throws Exception;

    void deleteShipping(Long id) throws Exception;
}
