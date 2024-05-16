package com.sid.msorder.Controller;


import com.sid.msorder.Dtos.OrderRequestDto;
import com.sid.msorder.Dtos.OrderResponseDto;
import com.sid.msorder.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("orders/order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{id}")
    ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long id)  {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @GetMapping("")
    ResponseEntity<Page<OrderResponseDto>> getAllOrders(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("field") String fields,
            @RequestParam("order") String order
    ){
        return ResponseEntity.ok(orderService.getAllOrders(pageNumber,pageSize,fields,order));
    }

    @PostMapping("")
    ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderRequestDto orderRequestDTO){

            OrderResponseDto orderResponseDto= orderService.AddOrder(orderRequestDTO);
            return ResponseEntity.ok(orderResponseDto);


    }
    @PutMapping("/{id}")
    ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long id,@RequestBody OrderRequestDto orderRequestDTO) {
        return ResponseEntity.ok(orderService.updateOrder(id,orderRequestDTO));
    }
   /* @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }*/

}
