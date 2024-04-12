package com.sid.usermicroservice.service;

import com.sid.usermicroservice.entities.User;
import com.sid.usermicroservice.exceptions.EmailAlreadyExistsException;
import com.sid.usermicroservice.exceptions.EmptyEntityException;
import com.sid.usermicroservice.exceptions.UserNotFoundException;
import com.sid.usermicroservice.dto.UserDto;
import com.sid.usermicroservice.dto.UserRequestDto;
import com.sid.usermicroservice.dto.UserResponseDto;

import java.util.List;

public interface IUserService {
    List<UserResponseDto> getAllUsers() throws UserNotFoundException;
    UserResponseDto getUserById(Long id) throws  UserNotFoundException, EmptyEntityException;
    UserDto createUser(UserRequestDto userDto) throws EmailAlreadyExistsException;
    User createUserAdmin(User user) throws EmailAlreadyExistsException;
    void deleteUser(Long id) throws  UserNotFoundException, EmptyEntityException;
    UserResponseDto updateUser(Long id, UserRequestDto userDto) throws UserNotFoundException;
    UserResponseDto getUserByEmail(String email) throws UserNotFoundException;
    UserResponseDto getUserByUsername(String username) throws UserNotFoundException;
    UserResponseDto getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException;
    UserResponseDto getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;
}
