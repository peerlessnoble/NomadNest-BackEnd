package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.SubCategoryRequestDTO;
import com.sid.msproduct.Dtos.SubCategoryResponseDTO;
import com.sid.msproduct.Entity.SubCategory;
import com.sid.msproduct.Repository.SubCategoryRepository;
import com.sid.msproduct.mappers.MappingProfiles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubCategoryServiceImpl implements SubCategoryService{
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategoryResponseDTO addSubcategory(SubCategoryRequestDTO subCategoryRequestDTO) {
        SubCategory subCategory = MappingProfiles.mapToEntity(subCategoryRequestDTO);
        System.out.println(subCategory);
        return MappingProfiles.mapToDto(subCategoryRepository.save(subCategory));
    }

    @Override
    public SubCategoryResponseDTO getSubCategoryById(Long id) throws Exception {
        SubCategory subCategory=subCategoryRepository.findById(id).orElseThrow(() -> new Exception("SubCategory not found"));
        return MappingProfiles.mapToDto(subCategory);
    }

    @Override
    public List<SubCategoryResponseDTO> getAllSubCategories() {
        return subCategoryRepository.findAll().stream().map(MappingProfiles::mapToDto).collect(Collectors.toList());
    }

    @Override
    public SubCategoryResponseDTO updateSubCategory(Long id, SubCategoryRequestDTO subCategoryRequestDTO) throws Exception {
        SubCategory subCategory=subCategoryRepository.findById(id).orElseThrow(() -> new Exception("Subcategory not found"));
        subCategory.setSubCategoryName(subCategoryRequestDTO.getSubCategoryName());
        subCategory.setDescription(subCategoryRequestDTO.getDescription());
        subCategory.setImagePath(subCategoryRequestDTO.getImagePath());

        return MappingProfiles.mapToDto(subCategoryRepository.save(subCategory)) ;
    }

    @Override
    public void deleteSubCategory(Long id) throws Exception {
        SubCategory subCategory=subCategoryRepository.findById(id).orElseThrow(() -> new Exception("Subcategory not found"));
        subCategoryRepository.delete(subCategory);


    }
}
