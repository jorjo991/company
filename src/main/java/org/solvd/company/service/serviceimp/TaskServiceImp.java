package org.solvd.company.service.serviceimp;

import org.solvd.company.domain.task.Task;
import org.solvd.company.persistence.TaskRepository;
import org.solvd.company.persistence.impl.TaskRepositoryImp;
import org.solvd.company.service.TaskService;

import java.util.List;

public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImp() {
        this.taskRepository = new TaskRepositoryImp();
    }

    @Override
    public void create(Task task, Long projectId) {
        taskRepository.create(task, projectId);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.get(id).
                orElseThrow(() -> new RuntimeException("Task not found with id " + id));

    }

    @Override
    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.readAll();
    }
}
