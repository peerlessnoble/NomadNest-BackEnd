package com.sid.msorder.Service;

import com.sid.msorder.Dtos.OrderItemRequestDTO;
import com.sid.msorder.Dtos.OrderItemResponseDTO;
import com.sid.msorder.Dtos.OrderRequestDTO;
import com.sid.msorder.Dtos.OrderResponseDTO;
import com.sid.msorder.Entity.Order;
import com.sid.msorder.Entity.OrderItem;
import com.sid.msorder.Exception.OrderNotValidException;
import com.sid.msorder.Exception.ValidatorException;
import com.sid.msorder.Repository.OrderRepository;
import com.sid.msorder.emails.EmailService;
import com.sid.msorder.mappers.MappingProfile;
import com.sid.msorder.utils.ValidationOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImp implements OrderService{
    private final OrderRepository orderRepository;
    private final EmailService emailService;



    @Override
    public Page<OrderResponseDTO> getAllOrders(int pageNumber, int pageSize, String field, String order) {
        PageRequest pageRequest=PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC:
                                Sort.Direction.ASC,
                        field)
        );
        return orderRepository.findAll(pageRequest).map(MappingProfile::mapToDto);
    }


    @Override
    public OrderResponseDTO getOrderById(Long id) throws OrderNotValidException {
        log.info("getOrder By Order" + id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotValidException("Order not found"));
        return MappingProfile.mapToDto(order);
    }

    @Override
    public OrderResponseDTO AddOrder(OrderRequestDTO orderRequestDTO) {
        if(orderRequestDTO.getOrderItems().isEmpty())
            throw new ValidatorException("OrderItems is Empty");
        Order order = MappingProfile.mapToEntity(orderRequestDTO);
        //here
        Order savedOrder = orderRepository.save(order);
        emailService.send("beloserlikeme@gmail.com", "New Order Added", "A new order has been added with ID: " + savedOrder.getId());
        return MappingProfile.mapToDto(orderRepository.save(order));
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotValidException("Order not found"));
        if (orderRequestDTO.getOrderItems() != null && !orderRequestDTO.getOrderItems().isEmpty()) {
            order.getOrderItems().clear();
            for (OrderItemRequestDTO itemDto : orderRequestDTO.getOrderItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(itemDto.getQuantity());
                orderItem.setUnitPrice(itemDto.getUnitPrice());
                orderItem.setOrder(order);
                order.getOrderItems().add(orderItem);
            }

        }
        order.setUpdatedDate(new Date()); // Set the updatedDate

        // Save the updated order
        Order savedOrder = orderRepository.save(order);
        return MappingProfile.mapToDto(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotValidException("Order not found"));
        orderRepository.delete(order);

    }

}
