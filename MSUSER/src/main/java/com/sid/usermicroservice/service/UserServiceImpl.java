package com.sid.usermicroservice.service;

import com.sid.usermicroservice.email.EmailSender;
import com.sid.usermicroservice.entities.ConfirmationToken;
import com.sid.usermicroservice.entities.User;
import com.sid.usermicroservice.enumerations.IsEnabled;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;
    //private final RabbitMqGetUserTasks rabbitMqGetUserTasks;
    private final PasswordEncoder passwordEncoder;
    private final TaskClient taskClient;
    private final ModelMapper modelMapper;
    private final ConfirmationTokenService confirmationTokenService;
    private final  EmailSender emailSender;


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
        //List<Task> tasks = taskClient.findByUserId(id);
       // List<Task> tasks = rabbitMqGetUserTasks.getUserTasks(id, "getUserTasksRoutingKey");
        //user.setTasks(tasks);
        return MappingProfile.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws EmailAlreadyExistsException {
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
                .isEnabled(IsEnabled.DISABLED).build();
        //return MappingProfile.mapToUserDto(userRepository.save(toSave));
        User user=userRepository.save(toSave);
        System.out.println(user);

        String token= UUID.randomUUID().toString();
        ConfirmationToken confirmationToken= new ConfirmationToken(
                token, LocalDateTime.now()
                ,LocalDateTime.now().plusMinutes(15)
                ,toSave);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        String link = "http://localhost:8888/users/confirm/" + token;
        emailSender.send(
                userRequestDto.getEmail(),
                buildEmail(userRequestDto.getFirstname(), link));
        return MappingProfile.toUserResponseDto(user);
    }
    @Override
    public User createUserAdmin(User user) {
        User toSave = User.builder()
            .username(user.getUsername())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .password(passwordEncoder.encode(user.getPassword()))
            .email(user.getEmail())
            .role(user.getRole())
            .isEnabled(IsEnabled.ENABLED).build();

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
    public UserDto updateUser(Long id, UserRequestDto userDto) throws UserNotFoundException {
        log.info("Creating new user: {}", userDto.getEmail());
        var validationErrors = UserInputValidation.validate(userDto);
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors);
        }
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(MessagesError.USER_NOT_FOUND.getMessage()));
        return MappingProfile.mapToUserDto(userRepository.save(user));
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
        if (user.isEmpty()  ) {
            throw new UserNotFoundException(
                    MessagesError.USER_NOT_FOUND_WITH_USERNAME_EQUALS.getMessage() + username);
        }
        else {
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
    public void enableAppUser(String email) {
         userRepository.enableUser(email);
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        enableAppUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
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
