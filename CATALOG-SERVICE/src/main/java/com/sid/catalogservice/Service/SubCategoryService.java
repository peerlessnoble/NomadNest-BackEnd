package com.sid.catalogservice.Service;

import com.sid.catalogservice.Dtos.SubCategoryRequestDTO;
import com.sid.catalogservice.Dtos.SubCategoryResponseDTO;

import java.util.List;

import com.sid.catalogservice.Exception.AlreadyExistException;
import com.sid.catalogservice.Exception.EmptyValueException;
import com.sid.catalogservice.Exception.NotFoundException;
import com.sid.catalogservice.Utility.QueryParams;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubCategoryService {
    SubCategoryResponseDTO addSubcategory(SubCategoryRequestDTO subCategoryRequestDTO) throws EmptyValueException, AlreadyExistException;
    SubCategoryResponseDTO getSubCategoryById(Long id) throws NotFoundException;
    Page<SubCategoryResponseDTO> getAllSubCategories(int pageNumber, int pageSize, String field, String order) throws NotFoundException;
    SubCategoryResponseDTO updateSubCategory(Long id,SubCategoryRequestDTO subCategoryRequestDTO) throws NotFoundException;
    void deleteSubCategory(Long id) throws NotFoundException;
    List<SubCategoryResponseDTO> findBySubcategoryName(String name) throws NotFoundException;
}
