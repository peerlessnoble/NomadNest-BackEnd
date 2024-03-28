package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.ProductRequestDto;
import com.sid.msproduct.Dtos.ProductResponseDto;
import com.sid.msproduct.Entity.Product;
import com.sid.msproduct.Repository.ProductRepository;
import com.sid.msproduct.mappers.MappingProfiles;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;


    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product= MappingProfiles.mapToEntity(productRequestDto);
        return MappingProfiles.mapToDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDto getProductById(Long id) throws Exception {
        Product product=productRepository.findById(id).orElseThrow( () -> new Exception("Product not found"));
        return MappingProfiles.mapToDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(MappingProfiles::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) throws Exception {
        Product product=productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        product.setProductName(productRequestDto.getProductName());
        product.setImagePath(productRequestDto.getImagePath());
        product.setLongDescription(productRequestDto.getLongDescription());
        product.setInStock(productRequestDto.getInStock());
        product.setShortDescription(productRequestDto.getShortDescription());
        product.setOriginalPrice(productRequestDto.getOriginalPrice());
        product.setSubCategory(productRequestDto.getSubCategory());
        return MappingProfiles.mapToDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        Product product=productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        productRepository.delete(product);

    }
}

