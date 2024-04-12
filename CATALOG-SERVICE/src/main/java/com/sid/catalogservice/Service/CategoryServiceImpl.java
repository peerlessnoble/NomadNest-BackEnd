package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.CategoryRequestDTO;
import com.sid.catalogservice.Dtos.CategoryResponseDTO;
import com.sid.catalogservice.Entity.Category;
import com.sid.catalogservice.Repository.CategoryRepository;
import com.sid.catalogservice.Utility.QueryParams;
import com.sid.catalogservice.mappers.MappingProfiles;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;


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
    public Page<CategoryResponseDTO> getAllCategories(QueryParams params) {
        PageRequest pageRequest  = PageRequest.of(
                params.getPageNumber(),
                params.getPageSize(),
                Sort.by(
                        params.getOrder().equalsIgnoreCase("desc")?
                                Sort.Direction.DESC
                                :Sort.Direction.ASC,
                        params.getField())
        );

        return categoryRepository.findAll(pageRequest).map(MappingProfiles::mapToDto);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
        if (categoryRequestDTO.getCategoryName()!=null)category.setCategoryName(categoryRequestDTO.getCategoryName());
        if (categoryRequestDTO.getDescription()!=null)category.setDescription(categoryRequestDTO.getDescription());
        if (categoryRequestDTO.getImagePath()!=null)category.setImagePath(categoryRequestDTO.getImagePath());
        return MappingProfiles.mapToDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
        categoryRepository.delete(category);

    }
}
