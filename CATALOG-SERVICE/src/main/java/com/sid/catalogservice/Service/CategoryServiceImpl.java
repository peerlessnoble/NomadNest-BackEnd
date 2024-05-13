package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.CategoryRequestDTO;
import com.sid.catalogservice.Dtos.CategoryResponseDTO;
import com.sid.catalogservice.Entity.Category;
import com.sid.catalogservice.Exception.EmptyValueException;
import com.sid.catalogservice.Exception.NotFoundException;
import com.sid.catalogservice.Utility.ValidationEntity;
import com.sid.catalogservice.Repository.CategoryRepository;
import com.sid.catalogservice.Utility.QueryParams;
import com.sid.catalogservice.mappers.MappingProfiles;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO) throws EmptyValueException {
        List<ErrorMessage> validationErrors = ValidationEntity.validateCategory(categoryRequestDTO);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(String.valueOf(validationErrors));
        }
        Category category= MappingProfiles.mapToEntity(categoryRequestDTO);
        return MappingProfiles.mapToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) throws NotFoundException {
        Category category=categoryRepository.findById(id).orElseThrow(() -> new  NotFoundException ("Category not found"));
        return MappingProfiles.mapToDto(category);
    }

    @Override
    public Page<CategoryResponseDTO> getAllCategories(int pageNumber, int pageSize, String field, String order) {
        PageRequest pageRequest=PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC:
                                Sort.Direction.ASC,
                        field)
        );

        return categoryRepository.findAll(pageRequest).map(MappingProfiles::mapToDto);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) throws  NotFoundException {
        Category category=categoryRepository.findById(id).orElseThrow(() -> new  NotFoundException("Category not found"));
        category.setCategoryName(categoryRequestDTO.getCategoryName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setImagePath(categoryRequestDTO.getImagePath());
        return MappingProfiles.mapToDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws NotFoundException {
        Category category=categoryRepository.findById(id).orElseThrow(() -> new  NotFoundException("Category not found"));
        categoryRepository.delete(category);

    }

    @Override
    public List<CategoryResponseDTO> findByCategoryName(String categoryName) throws NotFoundException {
        List<Category> categoryList=categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        return categoryList
                .stream()
                .map(MappingProfiles::mapToDto)
                .collect(Collectors.toList());


    }


}
