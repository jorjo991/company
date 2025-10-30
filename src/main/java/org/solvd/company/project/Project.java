package org.solvd.company.project;

import jakarta.xml.bind.annotation.*;
import org.solvd.company.employees.Employee;
import org.solvd.company.task.Task;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Project {

    @XmlAttribute(name = "id")
    private Long id;

    @XmlElement(name = "name")
    private String name;

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    private List<Task> tasks;
    @XmlElement(name = "finished")
    private boolean finished;

    public Project(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Project() {

    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                ", finished=" + finished +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
