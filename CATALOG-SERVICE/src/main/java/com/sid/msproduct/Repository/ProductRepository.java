package com.sid.msproduct.Repository;

import com.sid.msproduct.Dtos.ProductResponseDto;
import com.sid.msproduct.Entity.Product;
import com.sid.msproduct.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    //tester t les methodes
    //implemnetation de la pagination
    //exceptions
    //push git
    //git add.
    //git commit -m
    //git push origin layla

    Optional<Product> findProductBySubCategory(SubCategory subCategory);
    Optional<Product> findProductByProductName (String name);
    Optional<Product>findProductByShortDescription(String description);

}