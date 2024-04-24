package com.sid.catalogservice.Repository;

import com.sid.catalogservice.Entity.Product;
import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Entity.Product;
import com.sid.catalogservice.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<List<Product>> findProductBySubCategory(SubCategory subCategory_id);
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
    Optional<List<Product>> findProductByProductName(String productName);

   @Query("SELECT p FROM Product p WHERE p.shortDescription LIKE %:shortDescription%")
   Optional<List<Product>>findProductByShortDescription(String shortDescription);



}
