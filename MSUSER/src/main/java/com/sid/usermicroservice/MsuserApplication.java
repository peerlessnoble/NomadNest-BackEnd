package com.sid.usermicroservice;

import com.sid.usermicroservice.dto.UserResponseDto;
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
            UserRequestDto userRequestDto = new UserRequestDto(1L,"Amine","Khatmi",
                    "Khatmi04","code1234","AmineKhatmi@gmail.com",null);
            iUserService.createUser(userRequestDto);
            UserResponseDto user =iUserService.getUserByUsername("Khatmi04");
            System.out.println(user.getActive());
            System.out.println(user.getId());
            System.out.println(user.getRole());
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println(user.getTasks());



        };
    }

}
