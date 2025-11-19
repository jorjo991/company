package org.solvd.company.persistence;

import org.solvd.company.domain.task.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    void create(Task task, Long projectId);

    void update(Task t);

    Optional<Task> get(Long id);

    void delete(Task task);

    List<Task> readAll();

}
