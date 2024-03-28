package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.CategoryRequestDTO;
import com.sid.msproduct.Dtos.CategoryResponseDTO;
import com.sid.msproduct.Entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO getCategoryById(Long id) throws Exception;
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO categoryRequestDTO) throws Exception;
    void deleteCategory(Long id) throws Exception;

}
