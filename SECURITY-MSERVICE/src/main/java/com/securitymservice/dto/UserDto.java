package com.securitymservice.dto;

import lombok.Data;
import com.securitymservice.enums.Role;

@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private Role role;
}