package com.sid.msorder.Controller;


import com.sid.msorder.Dtos.OrderItemRequestDTO;
import com.sid.msorder.Dtos.OrderItemResponseDTO;
import com.sid.msorder.Dtos.OrderRequestDTO;
import com.sid.msorder.Dtos.OrderResponseDTO;

import com.sid.msorder.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("orders/order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{id}")
    ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @GetMapping("")
    ResponseEntity<Page<OrderResponseDTO>> getAllOrders(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("fields") String fields,
            @RequestParam("order") String order
    ){
        return ResponseEntity.ok(orderService.getAllOrders(pageNumber,pageSize,fields,order));
    }

    @PostMapping("")
    ResponseEntity<OrderResponseDTO> addOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return ResponseEntity.ok(orderService.AddOrder(orderRequestDTO));
    }
    @PutMapping("/{id}")
    ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id,@RequestBody OrderRequestDTO orderRequestDTO) throws Exception{
        return ResponseEntity.ok(orderService.updateOrder(id,orderRequestDTO));
    }
    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable Long id) throws Exception{
        orderService.deleteOrder(id);
    }
}

