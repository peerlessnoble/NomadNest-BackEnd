package com.nomadnest.clients.Catalog.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CATALOG-SERVICE")
public interface ProductServiceClient {
    @GetMapping("/catalog/products/{id}")
    Product getByProductById(@PathVariable Long id);
}
