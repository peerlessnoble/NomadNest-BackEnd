package com.sid.msorder.Controller;

import com.sid.msorder.Dtos.OrderItemResponseDTO;
import com.sid.msorder.Dtos.ShippingRequestDTO;
import com.sid.msorder.Dtos.ShippingResponseDTO;

import com.sid.msorder.Service.ShippingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders/shipping")
public class ShippingItemController {
    private final ShippingService shippingService;
    @GetMapping("/{id}")
    ResponseEntity<ShippingResponseDTO> getShippingById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok(shippingService.getShippingById(id));
    }
    @GetMapping("")
    ResponseEntity<Page<ShippingResponseDTO>> getAllShipping(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("fields") String fields,
            @RequestParam("order") String order
    ){
        return ResponseEntity.ok(shippingService.getAllShipping(pageNumber,pageSize,fields,order));
    }

    @PostMapping("/create")
    ResponseEntity<ShippingResponseDTO> addShipping(@RequestBody ShippingRequestDTO shippingRequestDTO){
        return ResponseEntity.ok(shippingService.AddShipping(shippingRequestDTO));
    }
    @PutMapping("/{id}")
    ResponseEntity<ShippingResponseDTO> updateShipping(@PathVariable Long id,@RequestBody ShippingRequestDTO shippingRequestDTO) throws Exception{
        return ResponseEntity.ok(shippingService.updateShipping(id,shippingRequestDTO));
    }
    @DeleteMapping("/{id}")
    void deleteShipping(@PathVariable Long id) throws Exception{
        shippingService.deleteShipping(id);
    }

}
