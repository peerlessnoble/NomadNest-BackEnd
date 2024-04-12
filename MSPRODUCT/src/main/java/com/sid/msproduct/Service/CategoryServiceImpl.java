package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.CategoryRequestDTO;
import com.sid.msproduct.Dtos.CategoryResponseDTO;
import com.sid.msproduct.Entity.Category;
import com.sid.msproduct.Repository.CategoryRepository;
import com.sid.msproduct.mappers.MappingProfiles;
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
    public Page<CategoryResponseDTO> getAllCategories(int pageNumber, int pageSize, String field, String order) {
        PageRequest pageRequest  = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(
                        order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC
                                :Sort.Direction.ASC,
                        field)
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
