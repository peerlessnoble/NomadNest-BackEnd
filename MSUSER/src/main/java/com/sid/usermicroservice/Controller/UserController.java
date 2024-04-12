package com.sid.usermicroservice.Controller;

import com.sid.usermicroservice.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sid.usermicroservice.dto.UserDto;
import com.sid.usermicroservice.dto.UserRequestDto;
import com.sid.usermicroservice.dto.UserResponseDto;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final IUserService iUserService;
    @GetMapping("/")
    //@PreAuthorize("hasRole('ADMIN')")
    public Collection<UserResponseDto> getAllUsers() {
        return iUserService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }
    @PostMapping("/register")
    public UserDto createUser(@RequestBody UserRequestDto user) {
        return iUserService.createUser(user);
    }

    @GetMapping("/username={username}")
    public UserResponseDto getUserByUsername(@PathVariable String username) {
        return iUserService.getUserByUsername(username);
    }
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(iUserService.getUserByEmail(email));
    }
    @GetMapping("/email={email}")
    public UserResponseDto findByEmail(@PathVariable String email) {
        return iUserService.getUserByEmail(email);
    }
    @DeleteMapping("/{id}")
    public UserResponseDto deleteUserBYID(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }
}
