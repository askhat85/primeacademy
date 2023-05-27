package com.prime.intern.demos.controller;

import com.prime.intern.demos.model.Task;
import com.prime.intern.demos.repository.TaskRepository;
import com.prime.intern.demos.service.TaskService;
import com.prime.intern.demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @PostMapping("/tasks")
    public Task create(@RequestBody Task task){
        task.setUserId(userService.getCurrentUser().getId());
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAll(){
        return taskRepository.findByUserId(userService.getCurrentUser().getId());
    }

    @GetMapping("/tasks/{id}")
    public Task getById(@PathVariable Long id){
        return taskService.getCurrentUsersTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task){
        Task usersTask = taskService.getCurrentUsersTaskById(id);
        if(usersTask!=null) {
            task.setId(id);
            task.setUserId(usersTask.getUserId());
            return taskRepository.save(task);
        }
        return null;
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id){
        Task task = taskService.getCurrentUsersTaskById(id);
        if(task!=null) {
            taskRepository.delete(task);
        }
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable Long id){
        Task task = taskService.getCurrentUsersTaskById(id);
        if(task!=null) {
            taskRepository.markAsDone(id);
        }
    }

    @PatchMapping("/tasks/{id}")
    public void patchMethod(@PathVariable Long id, @RequestBody Task task){
        if(task.getUserId() == userService.getCurrentUser().getId() && task.isDone()) {
            taskRepository.markAsDone(id);
        }
    }
}
