package com.sid.msproduct.Service;

import com.sid.msproduct.Dtos.SubCategoryRequestDTO;
import com.sid.msproduct.Dtos.SubCategoryResponseDTO;
import com.sid.msproduct.Utility.QueryParams;
import org.springframework.data.domain.Page;

public interface SubCategoryService {
    SubCategoryResponseDTO addSubcategory(SubCategoryRequestDTO subCategoryRequestDTO);
    SubCategoryResponseDTO getSubCategoryById(Long id) throws Exception;
    Page<SubCategoryResponseDTO> getAllSubCategories(QueryParams params);
    SubCategoryResponseDTO updateSubCategory(Long id,SubCategoryRequestDTO subCategoryRequestDTO) throws Exception;
    void deleteSubCategory(Long id) throws Exception;
}
