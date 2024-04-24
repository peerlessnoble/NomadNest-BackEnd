package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.ProductRequestDto;
import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Dtos.ProductRequestDto;
import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Entity.Product;
import com.sid.catalogservice.Entity.SubCategory;
import com.sid.catalogservice.Exception.NotFoundException;
import com.sid.catalogservice.Utility.QueryParams;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws ValidationException;
    ProductResponseDto getProductById(Long id) throws NotFoundException;

    Page<ProductResponseDto> getAllProducts(QueryParams params);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) throws  NotFoundException;
    void deleteProduct(Long id) throws NotFoundException;

    List<ProductResponseDto> findProductBySubCategory(SubCategory subcategory_id) throws NotFoundException;
    List<ProductResponseDto> findProductByProductName (String name) throws NotFoundException;
    List<ProductResponseDto> findProductByShortDescription(String description) throws NotFoundException;

}
