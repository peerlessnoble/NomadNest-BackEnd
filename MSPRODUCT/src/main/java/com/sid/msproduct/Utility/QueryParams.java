package com.sid.msproduct.Utility;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class QueryParams {
    private Integer pageNumber;
    private Integer pageSize;
    private String field;
    private String order;
}