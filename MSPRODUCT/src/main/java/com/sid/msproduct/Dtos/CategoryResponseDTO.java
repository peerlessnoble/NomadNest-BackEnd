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
public class CategoryResponseDTO {
    private Long id;
    private String categoryName;
    private String description;
    private String imagePath;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

}
