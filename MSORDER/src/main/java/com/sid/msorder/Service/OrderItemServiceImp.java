package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderItemRequestDTO;
import com.sid.msorder.Dtos.OrderItemResponseDTO;
import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Exception.OrderNotValidException;
import com.sid.msorder.Exception.ValidatorException;
import com.sid.msorder.Repository.OrderItemRepository;
import com.sid.msorder.mappers.MappingProfile;
import com.sid.msorder.utils.ValidationOrderItem;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImp implements OrderItemService {
    private final OrderItemRepository orderItemRepository;


    @Override
    public Page<OrderItemResponseDTO> getAllOrderItems(int pageNumber,int pageSize, String field,String order) {
        PageRequest pageRequest=PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                        Sort.Direction.DESC:
                        Sort.Direction.ASC,
                field)
        );

        return orderItemRepository.findAll(pageRequest).map(MappingProfile::mapToDto);
    }

    @Override
    public OrderItemResponseDTO getOrderItemById(Long id) throws OrderNotValidException {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderNotValidException("OrderItem not found"));

        return MappingProfile.mapToDto(orderItem);
    }

    @Override
    public OrderItemResponseDTO AddOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
        List<ErrorMessage> validationErrors = ValidationOrderItem.validate(orderItemRequestDTO);

        // Check if there are validation errors
        if (!validationErrors.isEmpty()) {

            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessage.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessage.toString());


        }
        OrderItem orderItem = MappingProfile.mapToEntity(orderItemRequestDTO);
        return MappingProfile.mapToDto(orderItemRepository.save(orderItem));
    }




    @Override
    public OrderItemResponseDTO updateOrderItem(Long id, OrderItemRequestDTO orderItemRequestDTO) throws Exception {
        List<ErrorMessage> validationErrors = ValidationOrderItem.validate(orderItemRequestDTO);
        if (!validationErrors.isEmpty()) {

            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ErrorMessage error : validationErrors) {
                errorMessage.append(error.getMessage()).append("; ");
            }
            throw new ValidatorException(errorMessage.toString());
        }

        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderNotValidException("Shipping not found"));
        orderItem.setQuantity(orderItemRequestDTO.getQuantity());
        //orderItem.setUnitPrice(orderItemRequestDTO.getUnitPrice());
        return MappingProfile.mapToDto(orderItemRepository.save(orderItem));
    }


    @Override
    public void deleteOrderItem(Long id) throws OrderNotValidException {
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new OrderNotValidException("Order item not found"));
        orderItemRepository.delete(orderItem);

    }
}


