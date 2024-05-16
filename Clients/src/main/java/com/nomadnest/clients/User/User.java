package com.nomadnest.clients.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User {
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;

}
