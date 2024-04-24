/*
package com.sid.msorder.feign;

import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Utility.QueryParams;
import com.sid.msorder.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("CATALOG-SERVICE")
public interface OrderInterface {
    @GetMapping("/")
    Product getAllProduct();
@GetMapping("/{id}")
ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) ;
@GetMapping("")
ResponseEntity<Page<ProductResponseDto>> getAllProducts(@RequestBody QueryParams params);
}
*/
