package com.sid.catalogservice.Dtos;

import com.sid.catalogservice.Entity.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;

    private String productName;

    private String shortDescription;

    private String longDescription;

    private Double originalPrice;

    private String imagePath;

    private Integer inStock;

    private Date createdAt;

    private Date deletedAt;

    private Date updatedAt;

    private SubCategoryResponseDTO subCategory;
    private Long user_id;
}
