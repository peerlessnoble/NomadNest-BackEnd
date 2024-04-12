package com.securitymservice.client;

import com.securitymservice.dto.RegisterDto;
import com.securitymservice.dto.UserDto;
import com.securitymservice.request.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MSUSER")
public interface UserServiceClient {
    @PostMapping("/users/register")
    ResponseEntity<RegisterDto> save(@RequestBody RegisterRequest request);

    @GetMapping("/users/username={username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username);
}
