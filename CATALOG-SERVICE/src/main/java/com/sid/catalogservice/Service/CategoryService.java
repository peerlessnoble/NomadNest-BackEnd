package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.CategoryRequestDTO;
import com.sid.catalogservice.Dtos.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO getCategoryById(Long id) throws Exception;
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO categoryRequestDTO) throws Exception;
    void deleteCategory(Long id) throws Exception;

}
