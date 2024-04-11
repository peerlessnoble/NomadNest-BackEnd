package com.sid.msproduct.Controller;

import com.sid.msproduct.Dtos.ProductRequestDto;
import com.sid.msproduct.Dtos.ProductResponseDto;
import com.sid.msproduct.Service.ProductService;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("")
    ResponseEntity< ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.addProduct(productRequestDto));
    }
@GetMapping("/{id}")
    ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(productService.getProductById(id));

    }
@GetMapping("")
   ResponseEntity<Page<ProductResponseDto>> getAllProducts(@RequestParam("pageNumber") int pageNumber,
                                                           @RequestParam("pageSize") int pageSize,
                                                           @RequestParam("field") String field,
                                                           @RequestParam("order") String order) {
        return ResponseEntity.ok(productService.getAllProducts(pageNumber,pageSize,field,order));
    }




@PutMapping("/{id}")
    ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) throws Exception {
        return ResponseEntity.ok(productService.updateProduct(id,productRequestDto));
    }
@DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) throws Exception {
        productService.deleteProduct(id);
    }




}
