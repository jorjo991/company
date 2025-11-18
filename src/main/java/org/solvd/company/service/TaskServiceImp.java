package org.solvd.company.service;

import org.solvd.company.domain.task.Task;
import org.solvd.company.persistence.impl.TaskRepositoryImp;
import org.solvd.company.service.interfcae.TaskService;

import java.util.List;

public class TaskServiceImp implements TaskService {

    private final TaskRepositoryImp taskRepositoryImp;

    public TaskServiceImp(TaskRepositoryImp taskRepositoryImp) {
        this.taskRepositoryImp = taskRepositoryImp;
    }

    @Override
    public void create(Task task, Long projectId) {
        taskRepositoryImp.create(task, projectId);
    }

    @Override
    public Task getTaskById(Long id) {
        Task task = taskRepositoryImp.get(id);
        if (task == null) {
            throw new RuntimeException("Task not found with id = " + id);
        }
        return task;
    }

    @Override
    public void updateTask(Task task) {
        taskRepositoryImp.update(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepositoryImp.delete(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryImp.readAll();
    }
}
