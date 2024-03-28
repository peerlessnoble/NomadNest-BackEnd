package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.CategoryRequestDTO;
import com.sid.msproduct.Dtos.CategoryResponseDTO;
import com.sid.msproduct.Entity.Category;
import com.sid.msproduct.Repository.CategoryRepository;
import com.sid.msproduct.mappers.MappingProfiles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category= MappingProfiles.mapToEntity(categoryRequestDTO);
        return MappingProfiles.mapToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
        return MappingProfiles.mapToDto(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(MappingProfiles::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
        if (categoryRequestDTO!=null)category.setCategoryName(categoryRequestDTO.getCategoryName());
        if (categoryRequestDTO!=null)category.setDescription(categoryRequestDTO.getDescription());
        if (categoryRequestDTO!=null)category.setImagePath(categoryRequestDTO.getImagePath());
        return MappingProfiles.mapToDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
        categoryRepository.delete(category);

    }
}
