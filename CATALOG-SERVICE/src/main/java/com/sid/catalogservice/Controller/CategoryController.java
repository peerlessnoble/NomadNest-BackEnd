package com.sid.catalogservice.Controller;

import com.sid.catalogservice.Dtos.CategoryRequestDTO;
import com.sid.catalogservice.Dtos.CategoryResponseDTO;
import com.sid.catalogservice.Service.CategoryService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/catalog/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping ("/{id}")
    ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) throws Exception{
       return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @GetMapping("")
   ResponseEntity<Page<CategoryResponseDTO>> getAllCategories(@RequestParam("pageNumber") int pageNumber,
                                                              @RequestParam("pageSize") int pageSize,
                                                              @RequestParam("field") String field,
                                                              @RequestParam("order") String order) {
        return ResponseEntity.ok(categoryService.getAllCategories(pageNumber,pageSize,field,order));
    }
    @PostMapping("")
    ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return ResponseEntity.ok(categoryService.addCategory(categoryRequestDTO));
    }
    @PutMapping("/{id}")
    ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,@RequestBody CategoryRequestDTO categoryRequestDTO) throws Exception{
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryRequestDTO));
    }
    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable Long id) throws Exception{
        categoryService.deleteCategory(id);
    }


}
