package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.CategoryRequestDTO;
import com.sid.catalogservice.Dtos.CategoryResponseDTO;
import com.sid.catalogservice.Entity.Category;
import com.sid.catalogservice.Exception.*;
import com.sid.catalogservice.Utility.QueryParams;
import org.springframework.data.domain.Page;
import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO) throws EmptyValueException, AlreadyExistException;
    CategoryResponseDTO getCategoryById(Long id) throws NotFoundException;
    Page<CategoryResponseDTO> getAllCategories(QueryParams params) throws NotFoundException;
    CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO categoryRequestDTO) throws NotFoundException;
    void deleteCategory(Long id) throws NotFoundException;
    List<CategoryResponseDTO>findByCategoryName(String name) throws NotFoundException;

}
