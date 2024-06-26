package com.sid.catalogservice.mappers;

import com.sid.catalogservice.Dtos.*;
import com.sid.catalogservice.Entity.Category;
import com.sid.catalogservice.Entity.Product;
import com.sid.catalogservice.Entity.SubCategory;
import org.modelmapper.ModelMapper;

public class MappingProfiles {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Product mapToEntity(ProductRequestDto productRequestDto) {
        return modelMapper.map(productRequestDto, Product.class);
    }

    public static ProductResponseDto mapToDto(Product product) {
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public static Category mapToEntity(CategoryRequestDTO categoryRequestDTO) {
        return modelMapper.map(categoryRequestDTO, Category.class);
    }

    public static CategoryResponseDTO mapToDto(Category category) {
        return modelMapper.map(category, CategoryResponseDTO.class);

    }

    public static SubCategory mapToEntity(SubCategoryRequestDTO subCategoryRequestDTO) {
        return modelMapper.map(subCategoryRequestDTO, SubCategory.class);
    }

    public static SubCategoryResponseDTO mapToDto(SubCategory subCategory) {
        return modelMapper.map(subCategory, SubCategoryResponseDTO.class);
    }
}