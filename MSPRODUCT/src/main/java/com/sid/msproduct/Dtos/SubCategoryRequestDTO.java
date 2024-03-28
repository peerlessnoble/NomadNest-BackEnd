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
public class SubCategoryRequestDTO {
    private Long id;
    private String subCategoryName;
    private Integer categoryId;
    private String description;
    private String imagePath;

}
