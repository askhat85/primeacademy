package com.prime.intern.demos.controller;

import com.prime.intern.demos.model.Task;
import com.prime.intern.demos.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @PostMapping("/tasks")
    public Task create(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAll(){
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task getById(@PathVariable Long id){
        return taskRepository.findById(id).orElse(null);
    }

    @PutMapping("/tasks/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

    @PatchMapping("/tasks/{id}:mark-as-done")
    public void patchMethod(@PathVariable Long id){
        taskRepository.markAsDone(id);
    }

    @PatchMapping("/tasks/{id}")
    public void patchMethod(@PathVariable Long id, @RequestBody Task task){
        if(task.isDone()) {
            taskRepository.markAsDone(id);
        }
    }
}
