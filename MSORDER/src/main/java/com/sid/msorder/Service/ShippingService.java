package com.sid.msorder.Service;

import com.sid.msorder.Dtos.ShippingRequestDto;
import com.sid.msorder.Dtos.ShippingResponseDto;
import com.sid.msorder.Exception.ShippingNotFound;
import org.springframework.data.domain.Page;

public interface ShippingService {
    Page<ShippingResponseDto> getAllShipping(int pageNumber, int pageSize, String field, String order);
    ShippingResponseDto getShippingById(Long shippingId) throws ShippingNotFound;
    ShippingResponseDto  AddShipping(ShippingRequestDto shippingRequestDTO);
    ShippingResponseDto updateShipping(Long shippingId, ShippingRequestDto shippingRequestDTO) throws ShippingNotFound;

    void deleteShipping(Long shippingId) throws ShippingNotFound;
}

