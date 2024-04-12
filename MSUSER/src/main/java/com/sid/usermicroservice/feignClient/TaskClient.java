package com.sid.usermicroservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.sid.usermicroservice.dto.Task;
import com.sid.usermicroservice.exceptions.UserNotFoundException;
import java.util.List;

@FeignClient(name="TASKS-SERVICE")
public interface TaskClient {
    @GetMapping("/users/{userId}")
    List<Task> findByUserId(@PathVariable("userId") Long userId) throws UserNotFoundException;
    @DeleteMapping("/tasks/{id}")
    String deleteTask(@PathVariable Long id) throws UserNotFoundException;

}
