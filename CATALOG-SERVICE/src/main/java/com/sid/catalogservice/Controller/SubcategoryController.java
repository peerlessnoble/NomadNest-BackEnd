package com.sid.catalogservice.Controller;

import com.sid.catalogservice.Dtos.SubCategoryRequestDTO;
import com.sid.catalogservice.Dtos.SubCategoryResponseDTO;
import com.sid.catalogservice.Service.SubCategoryService;
import com.sid.catalogservice.Utility.QueryParams;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/catalog/subcategories")
public class SubcategoryController {
    private final SubCategoryService subCategoryService;
    @PostMapping("")
    ResponseEntity<SubCategoryResponseDTO> addSubcategory(@RequestBody SubCategoryRequestDTO subCategoryRequestDTO) {
        return ResponseEntity.ok(subCategoryService.addSubcategory(subCategoryRequestDTO));
    }
@RequestMapping("/{id}")
    ResponseEntity< SubCategoryResponseDTO> getSubCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }
@RequestMapping("")
    ResponseEntity<Page<SubCategoryResponseDTO>>getAllSubCategories(@RequestBody QueryParams params) {
        return ResponseEntity.ok(subCategoryService.getAllSubCategories(params));
    }
@PutMapping("/{id}")
    ResponseEntity< SubCategoryResponseDTO> updateSubCategory(@PathVariable Long id, @RequestBody SubCategoryRequestDTO subCategoryRequestDTO) throws Exception {
        return ResponseEntity.ok(subCategoryService.updateSubCategory(id, subCategoryRequestDTO));
    }
@DeleteMapping("/{id}")
     void deleteSubCategory(@PathVariable Long id) throws Exception {
        subCategoryService.deleteSubCategory(id);
    }


}
