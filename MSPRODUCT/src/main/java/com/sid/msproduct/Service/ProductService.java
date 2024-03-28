package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.ProductRequestDto;
import com.sid.msproduct.Dtos.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(Long id) throws Exception;
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto updateProduct(Long id,ProductRequestDto productRequestDto) throws Exception;
    void deleteProduct(Long id) throws Exception;

}
