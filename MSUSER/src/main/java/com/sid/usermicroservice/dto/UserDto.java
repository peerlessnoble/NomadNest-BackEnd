package com.sid.usermicroservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sid.usermicroservice.entities.UserDetails;
import com.sid.usermicroservice.enumerations.Role;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
