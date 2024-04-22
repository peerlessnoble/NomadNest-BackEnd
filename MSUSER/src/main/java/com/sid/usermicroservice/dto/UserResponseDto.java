package com.sid.usermicroservice.dto;

import com.sid.usermicroservice.enumerations.Active;
import com.sid.usermicroservice.enumerations.Role;
import lombok.Data;

import java.util.List;
@Data
public class UserResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private  String username;
    private String email;
    private Role role;
    private Active active;
}
