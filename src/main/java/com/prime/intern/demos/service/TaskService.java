package com.prime.intern.demos.service;

import com.prime.intern.demos.model.Task;

public interface TaskService {
    public Task getCurrentUsersTaskById(Long id) throws Exception;

    public Task create(Task task);

    public Iterable<Task> getAllCurrentUsersTasks();

    public Task update(Long id, Task task) throws Exception;

    public void delete(Long id);

    public void markAsDone(Long id);

    public void markAsDone(Long id, Task task);
}
