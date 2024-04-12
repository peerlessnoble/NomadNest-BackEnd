package com.sid.usermicroservice.retrieve;

import com.sid.usermicroservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserExistenceListener {
    private final UserRepository userRepository;

    //@RabbitListener(queues = "${user.existence.queue}")
    //@SendTo("${user.existence.response.queue}")
    public String checkUserExistence(Long userId) {
        String response = String.valueOf(userRepository.existsById(userId));
        return response;
    }
}
