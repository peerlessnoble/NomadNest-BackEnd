package com.sid.msorder.Controller;

import com.sid.msorder.Dtos.OrderItemRequestDto;
import com.sid.msorder.Dtos.OrderItemResponseDto;
import com.sid.msorder.Service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/orders/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    @GetMapping("/{id}")
    ResponseEntity<OrderItemResponseDto> getOrderItem(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }
    @GetMapping
    ResponseEntity<Page<OrderItemResponseDto>> getAllOrderItem(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("fields") String fields,
            @RequestParam("order") String order
    ) {
        return ResponseEntity.ok(orderItemService.getAllOrderItems(pageNumber,pageSize,fields,order));
    }
    @PostMapping("/create")
    ResponseEntity<OrderItemResponseDto> addOrderItem(@RequestBody OrderItemRequestDto orderItemRequestDTO) {
        return ResponseEntity.ok(orderItemService.AddOrderItem(orderItemRequestDTO));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<OrderItemResponseDto> updateOrderItem(@PathVariable("id") Long orderItemId, @RequestBody OrderItemRequestDto orderItemRequestDTO) throws Exception {
        return ResponseEntity.ok(orderItemService.updateOrderItem(orderItemId, orderItemRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    void deleteOrderItem(@PathVariable("id") Long orderItemId) throws Exception {
        orderItemService.deleteOrderItem(orderItemId);
    }
}
