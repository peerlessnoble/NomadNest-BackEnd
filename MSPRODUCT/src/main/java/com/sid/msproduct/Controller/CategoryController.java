package com.sid.msproduct.Controller;

import com.sid.msproduct.Dtos.CategoryRequestDTO;
import com.sid.msproduct.Dtos.CategoryResponseDTO;
import com.sid.msproduct.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping ("/{id}")
    ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) throws Exception{
       return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @GetMapping("")
   ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
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
