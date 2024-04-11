package com.sid.msproduct.Dtos;

import com.sid.msproduct.Entity.SubCategory;
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

    private SubCategory subCategory;
}
