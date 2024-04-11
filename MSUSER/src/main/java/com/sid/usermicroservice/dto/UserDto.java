package com.sid.usermicroservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sid.usermicroservice.entities.UserDetails;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private UserDetails userDetails;
}
