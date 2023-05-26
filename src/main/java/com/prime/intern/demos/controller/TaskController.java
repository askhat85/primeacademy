package com.prime.intern.demos.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @PostMapping("/tasks")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Task create(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAll(){
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER','ROLE_ADMIN')")
    public Task getById(@PathVariable Long id){
        return taskRepository.findById(id).orElse(null);
    }

    @PutMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Task update(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void patchMethod(@PathVariable Long id){
        taskRepository.markAsDone(id);
    }

    @PatchMapping("/tasks/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void patchMethod(@PathVariable Long id, @RequestBody Task task){
        if(task.isDone()) {
            taskRepository.markAsDone(id);
        }
    }
}
