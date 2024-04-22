package com.nomadnest.clients.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("MSUSER")
public interface UserServiceClient {
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id);
}
