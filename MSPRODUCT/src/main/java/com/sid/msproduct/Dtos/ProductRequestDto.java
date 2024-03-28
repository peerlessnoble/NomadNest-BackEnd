package com.sid.msproduct.Dtos;

import com.sid.msproduct.Entity.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    private String productName;

    private String shortDescription;

    private String longDescription;
    private Double originalPrice;

    private String imagePath;
    private Integer inStock;
    private SubCategory subCategory;
    private Date createdAt;
}
