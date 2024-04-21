package com.sid.msorder.Controller;

import com.sid.msorder.Dtos.ShippingRequestDto;
import com.sid.msorder.Dtos.ShippingResponseDto;

import com.sid.msorder.Service.ShippingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/orders/shipping")
public class ShippingController {
    private final ShippingService shippingService;
    @GetMapping("/{id}")
    ResponseEntity<ShippingResponseDto> getShippingById(@PathVariable Long id) {
        return ResponseEntity.ok(shippingService.getShippingById(id));
    }
    @GetMapping("")
    ResponseEntity<Page<ShippingResponseDto>> getAllShipping(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("fields") String fields,
            @RequestParam("order") String order
    ){
        return ResponseEntity.ok(shippingService.getAllShipping(pageNumber,pageSize,fields,order));
    }

    @PostMapping("/create")
    ResponseEntity<ShippingResponseDto> addShipping(@RequestBody ShippingRequestDto shippingRequestDTO){
        return ResponseEntity.ok(shippingService.AddShipping(shippingRequestDTO));
    }
    @PutMapping("/{id}")
    ResponseEntity<ShippingResponseDto> updateShipping(@PathVariable Long id,@RequestBody ShippingRequestDto shippingRequestDTO) {
        return ResponseEntity.ok(shippingService.updateShipping(id,shippingRequestDTO));
    }
    @DeleteMapping("/{id}")
    void deleteShipping(@PathVariable Long id) {
        shippingService.deleteShipping(id);
    }

}
