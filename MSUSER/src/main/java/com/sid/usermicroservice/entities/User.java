package com.sid.usermicroservice.entities;

import com.sid.usermicroservice.enumerations.Active;
import com.sid.usermicroservice.enumerations.Role;
import jakarta.persistence.*;
import lombok.*;
import com.sid.usermicroservice.dto.Task;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "users")
@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @UpdateTimestamp
    private LocalDateTime updateTimestamp;
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

    public User(LocalDateTime creationTimestamp, String email, String username, String firstname, String lastname, String password, Role role, Active active) {
        this.creationTimestamp = creationTimestamp;
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public User(Long id, LocalDateTime creationTimestamp, LocalDateTime updateTimestamp, String email, String username, String firstname, String lastname, String password, Role role, Active active, UserDetails userDetails) {
        this.id = id;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.active = active;
        this.userDetails = userDetails;
    }
}
