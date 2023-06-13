package com.prime.intern.demos.controller;

import com.prime.intern.demos.model.Task;
import com.prime.intern.demos.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/tasks")
    public Task create(@RequestBody Task task){
        return taskService.create(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAll(){
        return taskService.getAllCurrentUsersTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getById(@PathVariable Long id){
        return taskService.getCurrentUsersTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task){
        return taskService.update(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable Long id){
        taskService.markAsDone(id);
    }

    @PatchMapping("/tasks/{id}")
    public void patchMethod(@PathVariable Long id, @RequestBody Task task){
        taskService.markAsDone(id, task);
    }
}
