package com.nomadnest.clients.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private  String username;
    private String email;
    private String role;
    private String active;
}
