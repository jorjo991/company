package org.solvd.company.persistence.DaoInteface;

import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.task.Task;

import java.util.List;

public interface TaskRepository {

    void  create(Task task, Long projectId);
    void  update(Task t);
    Task  get(Long id);
    void  delete(Task task);
    List<Task> readAll();

}
