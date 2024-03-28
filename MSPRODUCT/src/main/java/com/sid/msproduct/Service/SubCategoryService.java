package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.SubCategoryRequestDTO;
import com.sid.msproduct.Dtos.SubCategoryResponseDTO;

import java.util.List;

public interface SubCategoryService {
    SubCategoryResponseDTO addSubcategory(SubCategoryRequestDTO subCategoryRequestDTO);
    SubCategoryResponseDTO getSubCategoryById(Long id) throws Exception;
    List<SubCategoryResponseDTO> getAllSubCategories();
    SubCategoryResponseDTO updateSubCategory(Long id,SubCategoryRequestDTO subCategoryRequestDTO) throws Exception;
    void deleteSubCategory(Long id) throws Exception;
}
