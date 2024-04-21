package com.sid.msorder.Service;

import com.sid.msorder.Dtos.ShippingRequestDto;
import com.sid.msorder.Dtos.ShippingResponseDto;
import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.Shipping;
import com.sid.msorder.Exception.OrderNotFoundException;
import com.sid.msorder.Exception.ShippingNotFound;
import com.sid.msorder.Exception.ValidatorException;
import com.sid.msorder.Repository.OrderRepository;
import com.sid.msorder.Repository.ShippingRepository;
import com.sid.msorder.mappers.MappingProfile;
import com.sid.msorder.utils.ValidationShipping;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShippingServiceImp implements ShippingService{
    private final ShippingRepository shippingRepository;
    private final OrderRepository orderRepository;

    @Override
    public Page<ShippingResponseDto> getAllShipping(int pageNumber, int pageSize, String field, String order) {
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
    public ShippingResponseDto getShippingById(Long shippingId) throws ShippingNotFound {
        Shipping shipping = shippingRepository
                .findById(shippingId)
                .orElseThrow(() -> new ShippingNotFound("Shipping not found"));
        return MappingProfile.mapToDto(shipping);
    }
    @Override
    public ShippingResponseDto AddShipping(ShippingRequestDto shippingRequestDTO) {
        // Validate the shipping request DTO
        List<ErrorMessage> validationErrors = ValidationShipping.validate(shippingRequestDTO);
        if (!validationErrors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessage.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessage.toString());
        }


        Optional<Shipping> existingShipping = shippingRepository.findByOrderOrderId(shippingRequestDTO.getOrderId());
        if (existingShipping.isPresent()) {
            throw new ShippingNotFound("Shipping already exists for the given order");
        }
        Order order = orderRepository.findById(shippingRequestDTO.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        Shipping shipping = MappingProfile.mapToEntity(shippingRequestDTO);
        shipping.setOrder(order);
        Shipping savedShipping = shippingRepository.save(shipping);
        return MappingProfile.mapToDto(savedShipping);
    }


    @Override
    public ShippingResponseDto updateShipping(Long shippingId, ShippingRequestDto shippingRequestDTO) throws ShippingNotFound {
        List<ErrorMessage> validationErrors = ValidationShipping.validate(shippingRequestDTO);


        if (!validationErrors.isEmpty()) {

            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessage.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessage.toString());


        }
        Shipping shipping = shippingRepository
                .findById(shippingId)
                .orElseThrow(()-> new ShippingNotFound("Shipping not found"));
        shipping.setShippingAddress(shippingRequestDTO.getShippingAddress());
        shipping.setShippingCost(shippingRequestDTO.getShippingCost());

        return MappingProfile.mapToDto(shippingRepository.save(shipping));
    }
    @Override
    public void deleteShipping(Long shippingId) throws ShippingNotFound {
        Shipping shipping = shippingRepository
                .findById(shippingId)
                .orElseThrow(() -> new ShippingNotFound("Shipping not found"));
        shippingRepository.delete(shipping);
    }


}
