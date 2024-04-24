package com.sid.usermicroservice.entities;

import com.sid.usermicroservice.enumerations.IsEnabled;
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
    private IsEnabled isEnabled=IsEnabled.DISABLED;
    @Embedded
    private UserDetails userDetails;
    @Transient
    private List<Task> tasks;


}
