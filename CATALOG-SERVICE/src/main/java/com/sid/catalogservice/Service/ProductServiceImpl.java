package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.ProductRequestDto;
import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Entity.Product;
import com.sid.catalogservice.Repository.ProductRepository;
import com.sid.catalogservice.mappers.MappingProfiles;
import com.sid.catalogservice.Entity.SubCategory;
import com.sid.catalogservice.Repository.ProductRepository;
import com.sid.catalogservice.Repository.SubCategoryRepository;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;


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
    public Page<ProductResponseDto> getAllProducts(int pageNumber, int pageSize, String field, String order) {

        PageRequest pageRequest  = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(
                        order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC
                                :Sort.Direction.ASC,
                        field)
        );

        return productRepository.findAll(pageRequest).map(MappingProfiles::mapToDto);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) throws Exception {
        Product product=productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        if (productRequestDto.getProductName()!=null)product.setProductName(productRequestDto.getProductName());
        if (productRequestDto.getImagePath()!=null)product.setImagePath(productRequestDto.getImagePath());
        if (productRequestDto.getLongDescription()!=null)product.setLongDescription(productRequestDto.getLongDescription());
        if (productRequestDto.getInStock()!=null)product.setInStock(productRequestDto.getInStock());
        if (productRequestDto.getShortDescription()!=null)product.setShortDescription(productRequestDto.getShortDescription());
        if (productRequestDto.getOriginalPrice()!=null)product.setOriginalPrice(productRequestDto.getOriginalPrice());
        if (productRequestDto.getSubCategory()!=null)product.setSubCategory(productRequestDto.getSubCategory());
        return MappingProfiles.mapToDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        Product product=productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
        productRepository.delete(product);

    }




    @Override
    public ProductResponseDto findProductBySubCategory(Long subcategory_id) throws Exception {
        SubCategory subCategory = subCategoryRepository.findById(subcategory_id).get();
        Product product=productRepository.findProductBySubCategory(subCategory).orElseThrow(() -> new Exception("Product not found"));

        return MappingProfiles.mapToDto(product);
    }

    @Override
    public ProductResponseDto findProductByProductName(String name) throws Exception {
        Product product=productRepository.findProductByProductName(name).orElseThrow(() -> new Exception("Product not found"));
        return MappingProfiles.mapToDto(product);
    }


    @Override
    public ProductResponseDto findProductByShortDescription(String description) throws Exception {
        Product product=productRepository.findProductByShortDescription(description).orElseThrow(() -> new Exception("Product not found"));
        return MappingProfiles.mapToDto(product);
    }


}


