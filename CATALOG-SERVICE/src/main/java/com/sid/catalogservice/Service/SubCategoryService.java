package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.SubCategoryRequestDTO;
import com.sid.catalogservice.Dtos.SubCategoryResponseDTO;

import java.util.List;
import com.sid.catalogservice.Utility.QueryParams;
import org.springframework.data.domain.Page;

public interface SubCategoryService {
    SubCategoryResponseDTO addSubcategory(SubCategoryRequestDTO subCategoryRequestDTO);
    SubCategoryResponseDTO getSubCategoryById(Long id) throws Exception;
    Page<SubCategoryResponseDTO> getAllSubCategories(QueryParams params);
    SubCategoryResponseDTO updateSubCategory(Long id,SubCategoryRequestDTO subCategoryRequestDTO) throws Exception;
    void deleteSubCategory(Long id) throws Exception;
}
