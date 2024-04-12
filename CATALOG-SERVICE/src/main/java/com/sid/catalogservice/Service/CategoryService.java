package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.CategoryRequestDTO;
import com.sid.catalogservice.Dtos.CategoryResponseDTO;
import com.sid.catalogservice.Entity.Category;
import com.sid.catalogservice.Utility.QueryParams;
import org.springframework.data.domain.Page;
import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO getCategoryById(Long id) throws Exception;
    Page<CategoryResponseDTO> getAllCategories(QueryParams params);
    CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO categoryRequestDTO) throws Exception;
    void deleteCategory(Long id) throws Exception;

}
