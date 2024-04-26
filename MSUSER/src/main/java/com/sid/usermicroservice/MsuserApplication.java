package com.sid.usermicroservice;

import com.sid.usermicroservice.dto.UserDto;
import com.sid.usermicroservice.dto.UserResponseDto;
import com.sid.usermicroservice.entities.User;
import com.sid.usermicroservice.enumerations.Role;
import com.sid.usermicroservice.repositories.UserRepository;
import com.sid.usermicroservice.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.sid.usermicroservice.dto.UserRequestDto;

@SpringBootApplication
@EnableFeignClients
public class MsuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsuserApplication.class, args);
    }

    @Bean
    CommandLineRunner startApp(IUserService iUserService, UserRepository userRepository) {
        return args -> {
            User toSave = User.builder()
                    .username("Khatmi04")
                    .firstname("Amine")
                    .lastname("Khatmi")
                    .password("code1234")
                    .email("AmineKhatmi12@gmail.com")
                    .role(Role.ADMIN).build();
            iUserService.createUserAdmin(toSave);
            UserRequestDto userRequestDto = new UserRequestDto(1L,"Layla","Elaam",
                    "elaam04","code1234","AmineKhatmi11@gmail.com");
            iUserService.createUser(userRequestDto);
            UserDto user =iUserService.getUserByUsername("elaam04");
            System.out.println(user);
            System.out.println(user.getId());
            System.out.println(user.getRole());
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());



        };
    }

}
