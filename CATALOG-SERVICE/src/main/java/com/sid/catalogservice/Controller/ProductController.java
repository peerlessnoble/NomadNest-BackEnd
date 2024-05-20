package com.sid.catalogservice.Controller;

import com.sid.catalogservice.Dtos.ProductRequestDto;
import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Entity.SubCategory;
import com.sid.catalogservice.Exception.NotFoundException;
import com.sid.catalogservice.Exception.UnauthorizedException;
import com.sid.catalogservice.Service.ProductService;
import com.sid.catalogservice.Utility.QueryParams;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("")
    ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            ProductResponseDto responseDto = productService.addProduct(productRequestDto);
            return ResponseEntity.ok(responseDto);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // or provide a meaningful error response
        }
    }
    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(productService.getProductById(id));

    }
    @GetMapping("")
       ResponseEntity<Page<ProductResponseDto>> getAllProducts(@RequestParam("pageNumber") int pageNumber,
                                                               @RequestParam("pageSize") int pageSize,
                                                               @RequestParam("field") String field,
                                                               @RequestParam("order") String order) {
            return ResponseEntity.ok(productService.getAllProducts(pageNumber,pageSize,field,order));
    }
    @GetMapping("/subCategory/{subcategory_id}")
    ResponseEntity<List<ProductResponseDto> >findProductBySubCategory(@PathVariable SubCategory subcategory_id) throws NotFoundException{
        return ResponseEntity.ok(productService.findProductBySubCategory(subcategory_id));
    }
    @GetMapping("/name")
    ResponseEntity<List< ProductResponseDto> >findProductByProductName(@RequestParam("productName") String name) throws NotFoundException {
        return ResponseEntity.ok(productService.findProductByProductName(name));
    }
    @GetMapping("/description")
   ResponseEntity<List<ProductResponseDto>> findProductByShortDescription(@RequestParam("shortDescription") String description) throws NotFoundException {
       return ResponseEntity.ok(productService.findProductByShortDescription(description));
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) throws  NotFoundException {
        return ResponseEntity.ok(productService.updateProduct(id,productRequestDto));
    }
@DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) throws NotFoundException {
        productService.deleteProduct(id);
    }
    @GetMapping("/count")
    ResponseEntity<Integer> getProductCount() {
        return ResponseEntity.ok(this.productService.getProductsCount());
    }




}
