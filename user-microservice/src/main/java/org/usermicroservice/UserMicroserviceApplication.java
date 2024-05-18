package org.usermicroservice;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.usermicroservice.entities.Role;
import org.usermicroservice.entities.User;
import org.usermicroservice.enums.Active;
import org.usermicroservice.enums.ERole;
import org.usermicroservice.repositories.RoleRepository;
import org.usermicroservice.repositories.UserRepository;
import org.usermicroservice.services.IUserService;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class UserMicroserviceApplication {
    private final IUserService userService;
    private final RoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(UserMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            Role userRole = new Role(ERole.USER);
            Role adminRole = new Role(ERole.ADMIN);
            roleRepository.saveAll(List.of(adminRole,userRole));
            User amine = User.builder()
                    .firstname("Amine")
                    .lastname("khatmi")
                    .email("aminekhatmi12@gmail.com")
                    .numberPhone("0621403650")
                    .password("qwerty123")
                    .isActive(Active.ACTIVE)
                    .build();

            User naima = User.builder()
                    .firstname("Naima")
                    .lastname("Sabri")
                    .email("naimaSabri1504@gmail.com")
                    .numberPhone("0621403650")
                    .password("qwerty123")
                    .isActive(Active.ACTIVE)
                    .build();


            userService.registerUser(amine);
            userService.registerUser(naima);
            userService.addRoleToUserByEmail(ERole.ADMIN,"aminekhatmi12@gmail.com");




        };

    }


}
