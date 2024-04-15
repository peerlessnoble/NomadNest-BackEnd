package com.sid.msorder.Controller;

import com.sid.msorder.Dtos.OrderItemRequestDTO;
import com.sid.msorder.Dtos.OrderItemResponseDTO;
import com.sid.msorder.Service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    @GetMapping("/{id}")
    ResponseEntity<OrderItemResponseDTO> getOrderItem(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }
    @GetMapping
    ResponseEntity<Page<OrderItemResponseDTO>> getAllOrderItem(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("fields") String fields,
            @RequestParam("order") String order
    ) {
        return ResponseEntity.ok(orderItemService.getAllOrderItems(pageNumber,pageSize,fields,order));
    }
    @PostMapping("/create")
    ResponseEntity<OrderItemResponseDTO> addOrderItem(@RequestBody OrderItemRequestDTO orderItemRequestDTO) {
        return ResponseEntity.ok(orderItemService.AddOrderItem(orderItemRequestDTO));
    }
    @PutMapping("/update/{id}")
    ResponseEntity<OrderItemResponseDTO> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequestDTO orderItemRequestDTO) throws Exception {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItemRequestDTO));
    }
    @DeleteMapping("/delete/{id}")
    void deleteOrderItem(@PathVariable Long id) throws Exception {
        orderItemService.deleteOrderItem(id);
    }
}


