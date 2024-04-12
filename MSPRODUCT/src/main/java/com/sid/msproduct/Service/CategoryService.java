package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.CategoryRequestDTO;
import com.sid.msproduct.Dtos.CategoryResponseDTO;
import com.sid.msproduct.Entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO getCategoryById(Long id) throws Exception;
    Page<CategoryResponseDTO> getAllCategories(int pageNumber, int pageSize, String field, String order);
    CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO categoryRequestDTO) throws Exception;
    void deleteCategory(Long id) throws Exception;

}
