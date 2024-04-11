package com.sid.usermicroservice.entities;

import com.sid.usermicroservice.enumerations.Active;
import com.sid.usermicroservice.enumerations.Role;
import jakarta.persistence.*;
import lombok.*;
import com.sid.usermicroservice.dto.Task;

import java.util.List;

@Entity(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    @Column(unique = true, nullable = false, updatable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Active active;
    @Embedded
    private UserDetails userDetails;
    @Transient
    private List<Task> tasks;
}
