package com.sid.usermicroservice.service;

import com.sid.usermicroservice.entities.User;
import com.sid.usermicroservice.enumerations.Active;
import com.sid.usermicroservice.enumerations.MessagesError;
import com.sid.usermicroservice.enumerations.Role;
import com.sid.usermicroservice.exceptions.EmailAlreadyExistsException;
import com.sid.usermicroservice.exceptions.EmptyEntityException;
import com.sid.usermicroservice.exceptions.UserNotFoundException;
import com.sid.usermicroservice.feignClient.TaskClient;
import com.sid.usermicroservice.mappers.MappingProfile;
import com.sid.usermicroservice.repositories.UserRepository;
import com.sid.usermicroservice.retrieve.RabbitMqGetUserTasks;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sid.usermicroservice.dto.Task;
import com.sid.usermicroservice.dto.UserDto;
import com.sid.usermicroservice.dto.UserRequestDto;
import com.sid.usermicroservice.dto.UserResponseDto;
import com.sid.usermicroservice.utils.UserInputValidation;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;
    //private final RabbitMqGetUserTasks rabbitMqGetUserTasks;
    private final PasswordEncoder passwordEncoder;
    private final TaskClient taskClient;
    private final ModelMapper modelMapper;

    @Override
    public List<UserResponseDto> getAllUsers() throws UserNotFoundException {
        log.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(MappingProfile::toUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(Long id) throws UserNotFoundException, EmptyEntityException {
        log.info("Fetching user by id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        MessagesError.USER_NOT_FOUND_WITH_ID_EQUALS.getMessage() + id));
//      List<Task> tasks = taskClient.findByUserId(id);
       // List<Task> tasks = rabbitMqGetUserTasks.getUserTasks(id, "getUserTasksRoutingKey");
        //user.setTasks(tasks);
        return MappingProfile.toUserResponseDto(user);
    }

    @Override
    public UserDto createUser(UserRequestDto userRequestDto) throws EmailAlreadyExistsException {
        log.info("Creating new user with email : {}", userRequestDto.getEmail());
        var validationErrors = UserInputValidation.validate(userRequestDto);
        if (!validationErrors.isEmpty()) throw new ValidationException(validationErrors);
        User toSave = User.builder()
                .username(userRequestDto.getUsername())
                .firstname(userRequestDto.getFirstname())
                .lastname(userRequestDto.getLastname())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .email(userRequestDto.getEmail())
                .role(Role.USER)
                .active(Active.ACTIVE).build();
        //return MappingProfile.mapToUserDto(userRepository.save(toSave));
        return modelMapper.map(userRepository.save(toSave), UserDto.class);
    }
    @Override
    public User createUserAdmin(User user) {

        User toSave = new User(
                LocalDateTime.now(),
                user.getEmail(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                passwordEncoder.encode(user.getPassword()),
                Role.ADMIN,
                Active.ACTIVE);
        return userRepository.save(toSave);
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException, EmptyEntityException {
        log.info("delete user by id : {}", userId);

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(MessagesError.USER_NOT_FOUND_WITH_ID_EQUALS.getMessage() + userId);
        }
        userRepository.deleteUserById(userId);
        rabbitTemplate.convertAndSend("tasksExchange", "tasksRouting", userId);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userDto) throws UserNotFoundException {
        log.info("Creating new user: {}", userDto.getEmail());
        var validationErrors = UserInputValidation.validate(userDto);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(MessagesError.USER_NOT_FOUND.getMessage()));
        return MappingProfile.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        log.info("Fetching user by email: {}", email);
        return userRepository.findUserByEmail(email)
                .map(MappingProfile::mapToUserDto)
                .orElseThrow(() -> new UserNotFoundException(
                        MessagesError.USER_NOT_FOUND_WITH_EMAIL_EQUALS.getMessage() + email));
    }

    @Override
    public UserDto getUserByUsername(String username) throws UserNotFoundException {
        log.info("Fetching user by username: {}", username);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException(
                    MessagesError.USER_NOT_FOUND_WITH_USERNAME_EQUALS.getMessage() + username);
        } else {
            return MappingProfile.mapToUserDto(user.get());
        }
    }

    @Override
    public UserDto getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        log.info("Fetching user by username and password : {}", username + "Password : *******");
        return userRepository.findByUsernameAndPassword(username, password)
                .map(MappingProfile::mapToUserDto)
                .orElseThrow(() -> new UserNotFoundException(MessagesError.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        log.info("Fetching user by email and password : {}", email + "Password : *******");
        return userRepository.findByEmailAndPassword(email, password)
                .map(MappingProfile::mapToUserDto)
                .orElseThrow(() -> new UserNotFoundException(
                        MessagesError.USER_NOT_FOUND.getMessage()));
    }

    //    @RabbitListener(queues = "isUserIdExistQueue")
//    public ResponseEntity<?> receiveAnswer(Long userId) throws NotFoundException {
//        if (userRepo.existsById(userId)) {
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        }else {
//            rabbitTemplate.convertAndSend("tasksExchange", "tasksRouting", userId);
//            throw new NotFoundException("User with id: "+userId+" NOT found!");
//        }
//
//    }
}
