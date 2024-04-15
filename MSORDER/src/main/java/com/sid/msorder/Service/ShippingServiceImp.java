package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderItemResponseDTO;
import com.sid.msorder.Dtos.ShippingRequestDTO;
import com.sid.msorder.Dtos.ShippingResponseDTO;
import com.sid.msorder.Entity.Shipping;
import com.sid.msorder.Exception.OrderNotValidException;
import com.sid.msorder.Exception.ShippingNotFound;
import com.sid.msorder.Repository.ShippingRepository;
import com.sid.msorder.mappers.MappingProfile;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShippingServiceImp implements ShippingService{
    private final ShippingRepository shippingRepository;


    @Override
    public Page<ShippingResponseDTO> getAllShipping(int pageNumber, int pageSize, String field, String order) {
        PageRequest pageRequest=PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC:
                                Sort.Direction.ASC,
                        field)
        );
        return shippingRepository.findAll(pageRequest).map(MappingProfile::mapToDto);
    }


    @Override
    public ShippingResponseDTO getShippingById(Long id) throws Exception {
        Shipping shipping = shippingRepository
                .findById(id)
                .orElseThrow(() -> new OrderNotValidException("Shipping not found"));
        return MappingProfile.mapToDto(shipping);
    }

    @Override
    public ShippingResponseDTO AddShipping(ShippingRequestDTO shippingRequestDTO) {
        Shipping shipping = MappingProfile.mapToEntity(shippingRequestDTO);
        return MappingProfile.mapToDto(shippingRepository.save(shipping));
    }

    @Override
    public ShippingResponseDTO updateShipping(Long id, ShippingRequestDTO shippingRequestDTO) throws RuntimeException {
        Shipping shipping = shippingRepository
                .findById(id)
                .orElseThrow(()-> new ShippingNotFound("Shipping not found"));
        shipping.setShippingAddress(shippingRequestDTO.getShippingAddress());
        shipping.setStatus(shippingRequestDTO.getStatus());
        shipping.setTrackingNumber(shippingRequestDTO.getTrackingNumber());
        shipping.setShippingCost(shippingRequestDTO.getShippingCost());

        return MappingProfile.mapToDto(shippingRepository.save(shipping));
    }

    @Override
    public void deleteShipping(Long id) throws RuntimeException {
        Shipping shipping = shippingRepository
                .findById(id)
                .orElseThrow(() -> new ShippingNotFound("Shipping not found"));
        shippingRepository.delete(shipping);
    }

}
