package com.sid.catalogservice.Repository;

import com.sid.catalogservice.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE%:categoryName%")

    Optional<List<Category>> findByCategoryName(String categoryName);
}
