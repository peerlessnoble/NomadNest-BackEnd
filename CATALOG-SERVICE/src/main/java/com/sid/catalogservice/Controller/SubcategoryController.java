package com.sid.catalogservice.Controller;

import com.sid.catalogservice.Dtos.SubCategoryRequestDTO;
import com.sid.catalogservice.Dtos.SubCategoryResponseDTO;
import com.sid.catalogservice.Exception.AlreadyExistException;
import com.sid.catalogservice.Exception.EmptyValueException;
import com.sid.catalogservice.Exception.NotFoundException;
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
    ResponseEntity<SubCategoryResponseDTO> addSubcategory(@RequestBody SubCategoryRequestDTO subCategoryRequestDTO) throws AlreadyExistException, EmptyValueException {

        return ResponseEntity.ok(subCategoryService.addSubcategory(subCategoryRequestDTO));
    }
@RequestMapping("/{id}")
    ResponseEntity< SubCategoryResponseDTO> getSubCategoryById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }
@RequestMapping("")
    ResponseEntity<Page<SubCategoryResponseDTO>>getAllSubCategories(@RequestBody QueryParams params) {
        return ResponseEntity.ok(subCategoryService.getAllSubCategories(params));
    }
@GetMapping("/name")
   ResponseEntity<List<SubCategoryResponseDTO>> findBySubcategoryName(@RequestParam("subcategoryName") String name) throws NotFoundException {
        return ResponseEntity.ok(subCategoryService.findBySubcategoryName(name));
    }


@PutMapping("/{id}")
    ResponseEntity< SubCategoryResponseDTO> updateSubCategory(@PathVariable Long id, @RequestBody SubCategoryRequestDTO subCategoryRequestDTO) throws NotFoundException {
        return ResponseEntity.ok(subCategoryService.updateSubCategory(id, subCategoryRequestDTO));
    }
@DeleteMapping("/{id}")
     void deleteSubCategory(@PathVariable Long id) throws NotFoundException {
        subCategoryService.deleteSubCategory(id);
    }


}
