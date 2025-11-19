package org.solvd.company.service;

import org.solvd.company.domain.task.Task;

import java.util.List;

public interface TaskService {

    void create(Task task, Long projectID);

    Task getTaskById(Long id);

    void updateTask(Task task);

    void deleteTask(Task task);

    List<Task> getAllTasks();
}
