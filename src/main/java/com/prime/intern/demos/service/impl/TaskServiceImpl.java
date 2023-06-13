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
        Iterable<Task> task = getAllCurrentUsersTasks();
        for (Task t: task) {
            if(t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    @Override
    public Task create(Task task) {
        task.setUserId(userService.getCurrentUser().getId());
        return taskRepository.save(task);
    }

    @Override
    public Iterable<Task> getAllCurrentUsersTasks() {
        return taskRepository.findByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public Task update(Long id, Task task) {
        Task usersTask = getCurrentUsersTaskById(id);
        if(usersTask!=null) {
            task.setId(id);
            task.setUserId(usersTask.getUserId());
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if(getCurrentUsersTaskById(id)!=null) {
            taskRepository.deleteById(id);
        }
    }

    @Override
    public void markAsDone(Long id) {
        if(getCurrentUsersTaskById(id)!=null) {
            taskRepository.markAsDone(id);
        }
    }

    @Override
    public void markAsDone(Long id, Task task) {
        if(getCurrentUsersTaskById(id)!=null && task.isDone()) {
            taskRepository.markAsDone(id);
        }
    }


}
