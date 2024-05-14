package com.sid.catalogservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "description")
    private String description;
    @Column(name = "image_path")
    private String imagePath;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
//    @Column(name = "deleted_at")
//    private Date deletedAt;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SubCategory> subCategoryList = new ArrayList<>();
    public void addSubCategory(SubCategory subCategory){
        subCategory.setCategory(this);
        if (subCategoryList == null) {
            subCategoryList = new ArrayList<>();
        }
        subCategoryList.add(subCategory);
    }
    public void removeSubCategory(SubCategory subCategory){
        subCategoryList.remove(subCategory);
    }

}
