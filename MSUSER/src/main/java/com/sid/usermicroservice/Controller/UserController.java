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
    public ResponseEntity<UserResponseDto>  createUser(@RequestBody UserRequestDto user) {
        return ResponseEntity.ok(iUserService.createUser(user));
    }
    @GetMapping("/confirm/{token}")
    public ResponseEntity<String>  createUser(@PathVariable String token) {
        return ResponseEntity.ok(iUserService.confirmToken(token));
    }

    @GetMapping("/username={username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return iUserService.getUserByUsername(username);
    }
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(iUserService.getUserByEmail(email));
    }
    @GetMapping("/email={email}")
    public UserDto findByEmail(@PathVariable String email) {
        return iUserService.getUserByEmail(email);
    }
    @DeleteMapping("/{id}")
    public UserResponseDto deleteUserBYID(@PathVariable Long id) {
        return iUserService.getUserById(id);
    }
}
