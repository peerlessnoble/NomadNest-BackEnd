package com.sid.catalogservice.Service;

import com.sid.catalogservice.Exception.EmptyValueException;
import com.sid.catalogservice.Exception.NotFoundException;
import com.sid.catalogservice.Utility.ValidationEntity;
import com.sid.catalogservice.Repository.SubCategoryRepository;
import com.sid.catalogservice.Dtos.SubCategoryRequestDTO;
import com.sid.catalogservice.Dtos.SubCategoryResponseDTO;
import com.sid.catalogservice.Entity.SubCategory;
import com.sid.catalogservice.mappers.MappingProfiles;
import com.sid.catalogservice.Utility.QueryParams;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategoryResponseDTO addSubcategory(SubCategoryRequestDTO subCategoryRequestDTO) throws EmptyValueException {
        List<ErrorMessage> validationErrors = ValidationEntity.validateSubcategory(subCategoryRequestDTO);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(String.valueOf(validationErrors));
        }

        SubCategory subCategory = MappingProfiles.mapToEntity(subCategoryRequestDTO);
            return MappingProfiles.mapToDto(subCategoryRepository.save(subCategory));
        }



    @Override
    public SubCategoryResponseDTO getSubCategoryById(Long id) throws NotFoundException {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Subcategory with id " + id + " not found"));
        return MappingProfiles.mapToDto(subCategory);
    }

    @Override
    public Page<SubCategoryResponseDTO> getAllSubCategories(int pageNumber, int pageSize, String field, String order) throws NotFoundException {
        PageRequest pageRequest=PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC:
                                Sort.Direction.ASC,
                        field)
        );

        return subCategoryRepository.findAll(pageRequest).map(MappingProfiles::mapToDto);
    }

    @Override
    public SubCategoryResponseDTO updateSubCategory(Long id, SubCategoryRequestDTO subCategoryRequestDTO) throws NotFoundException {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Subcategory with id " + id + " not found"));
        subCategory.setSubCategoryName(subCategoryRequestDTO.getSubCategoryName());
        subCategory.setDescription(subCategoryRequestDTO.getDescription());
        subCategory.setImagePath(subCategoryRequestDTO.getImagePath());

        return MappingProfiles.mapToDto(subCategoryRepository.save(subCategory));
    }

    @Override
    public void deleteSubCategory(Long id) throws NotFoundException {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Subcategory with id " + id + " not found"));
        subCategoryRepository.delete(subCategory);
    }

    @Override
    public List<SubCategoryResponseDTO> findBySubcategoryName(String subcategoryName) throws NotFoundException {
       List<SubCategory> subCategoryList = subCategoryRepository.findBySubCategoryName(subcategoryName).orElseThrow(() -> new NotFoundException("Subcategory with name '" + subcategoryName + "' not found"));
        return subCategoryList.stream().map(MappingProfiles::mapToDto).collect(Collectors.toList()) ;
    }
}
