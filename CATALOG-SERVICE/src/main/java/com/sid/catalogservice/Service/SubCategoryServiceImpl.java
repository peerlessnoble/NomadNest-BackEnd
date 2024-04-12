package com.sid.catalogservice.Service;

import com.sid.catalogservice.Repository.SubCategoryRepository;
import com.sid.catalogservice.Dtos.SubCategoryRequestDTO;
import com.sid.catalogservice.Dtos.SubCategoryResponseDTO;
import com.sid.catalogservice.Entity.SubCategory;
import com.sid.catalogservice.mappers.MappingProfiles;
import com.sid.catalogservice.Utility.QueryParams;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubCategoryServiceImpl implements SubCategoryService{
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategoryResponseDTO addSubcategory(SubCategoryRequestDTO subCategoryRequestDTO) {
        SubCategory subCategory = MappingProfiles.mapToEntity(subCategoryRequestDTO);
        return MappingProfiles.mapToDto(subCategoryRepository.save(subCategory));
    }

    @Override
    public SubCategoryResponseDTO getSubCategoryById(Long id) throws Exception {
        SubCategory subCategory=subCategoryRepository.findById(id).orElseThrow(() -> new Exception("SubCategory not found"));
        return MappingProfiles.mapToDto(subCategory);
    }

    @Override
    public Page<SubCategoryResponseDTO> getAllSubCategories(QueryParams params) {
        Sort.Direction direction = params
                .getOrder().
                equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        PageRequest pageRequest=PageRequest.of(
                params.getPageNumber(),
                params.getPageSize(),
                Sort.by(direction,params.getField()));

        return subCategoryRepository.findAll(pageRequest).map(MappingProfiles::mapToDto);
    }

    @Override
    public SubCategoryResponseDTO updateSubCategory(Long id, SubCategoryRequestDTO subCategoryRequestDTO) throws Exception {
        SubCategory subCategory=subCategoryRepository.findById(id).orElseThrow(() -> new Exception("Subcategory not found"));
        if (subCategoryRequestDTO.getSubCategoryName()!=null)subCategory.setSubCategoryName(subCategoryRequestDTO.getSubCategoryName());
        if (subCategoryRequestDTO.getDescription()!=null)subCategory.setDescription(subCategoryRequestDTO.getDescription());
        if (subCategoryRequestDTO.getImagePath()!=null)subCategory.setImagePath(subCategoryRequestDTO.getImagePath());

        return MappingProfiles.mapToDto(subCategoryRepository.save(subCategory)) ;
    }

    @Override
    public void deleteSubCategory(Long id) throws Exception {
        SubCategory subCategory=subCategoryRepository.findById(id).orElseThrow(() -> new Exception("Subcategory not found"));
        subCategoryRepository.delete(subCategory);


    }
}
