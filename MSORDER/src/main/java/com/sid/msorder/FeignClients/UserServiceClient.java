package com.sid.msorder.FeignClients;

import com.sid.msorder.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MSUSER")
public interface UserServiceClient {
    @PostMapping("/users/{id}")
    User getUserById(@PathVariable Long id);

}