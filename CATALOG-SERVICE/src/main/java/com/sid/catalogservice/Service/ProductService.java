package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.ProductRequestDto;
import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Dtos.ProductRequestDto;
import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Entity.Product;
import com.sid.catalogservice.Utility.QueryParams;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws Exception;
    ProductResponseDto getProductById(Long id) throws Exception;
    Page<ProductResponseDto> getAllProducts(QueryParams params);
    ProductResponseDto updateProduct(Long id,ProductRequestDto productRequestDto) throws Exception;
    void deleteProduct(Long id) throws Exception;

    ProductResponseDto findProductBySubCategory(Long subcategory_id) throws Exception;
    ProductResponseDto findProductByProductName (String name) throws Exception;
    ProductResponseDto findProductByShortDescription(String description) throws Exception;

}
