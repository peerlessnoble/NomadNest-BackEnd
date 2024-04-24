package com.sid.catalogservice.Repository;

import com.sid.catalogservice.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    @Query("SELECT c FROM SubCategory c WHERE c.subCategoryName LIKE %:subCategoryName%")
    Optional<List<SubCategory>> findBySubCategoryName(String subCategoryName);
}
