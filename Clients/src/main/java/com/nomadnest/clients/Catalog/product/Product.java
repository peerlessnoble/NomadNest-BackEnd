package com.nomadnest.clients.Catalog.product;

import com.nomadnest.clients.Catalog.SubCategory.SubCategory;

import java.util.Date;

public class Product {

    private String productName;

    private String shortDescription;

    private String longDescription;
    private Double originalPrice;

    private String imagePath;
    private Integer inStock;
    private SubCategory subCategory;
    private Date createdAt;
}
