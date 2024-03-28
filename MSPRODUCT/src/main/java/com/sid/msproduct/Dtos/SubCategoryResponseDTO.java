package com.sid.msproduct.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryResponseDTO {
    private Long id;
    private String subCategoryName;
    private Integer categoryId;
    private String description;
    private String imagePath;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

}
