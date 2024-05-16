package com.nomadnest.clients.Catalog.product;

import com.nomadnest.clients.Catalog.SubCategory.SubCategory;
import lombok.Data;

import java.util.Date;
@Data
public class Product {
    private Long id;

    private String productName;

    private String shortDescription;

    private String longDescription;
    private Double originalPrice;

    private String imagePath;
    private Integer inStock;
    private Date createdAt;
}
