package com.prime.intern.demos.service.impl;

import com.prime.intern.demos.model.Task;
import com.prime.intern.demos.repository.TaskRepository;
import com.prime.intern.demos.service.TaskService;
import com.prime.intern.demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Override
    public Task getCurrentUsersTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.map(Task::getUserId).orElse(null) == userService.getCurrentUser().getId()) {
            return task.get();
        }
        return null;
    }

}
