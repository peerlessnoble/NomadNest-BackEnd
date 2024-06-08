package org.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class LoginResponseDTO {
    private Long userId;
    private String email;
    private String firstname;
    private String lastname;
    private String accessToken;
    private String refreshToken;
    private List<String> roles= new ArrayList<>();
    private String status;
}
