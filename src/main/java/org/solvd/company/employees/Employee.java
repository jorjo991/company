package org.solvd.company.employees;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.solvd.company.person.Person;
import org.solvd.company.task.Task;

import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Employee extends Person {
    @XmlElement(name = "EmployeeType")
    private EmployeeType employeeType;
    @XmlElement(name = "WorksOnProject")
    private String worksOnProject;
    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    private List<Task> tasks;

    public Employee(int age, String name, String surname, String email, Date birthDay) {
        super(age, name, surname, email, birthDay);
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() +
                "employeeType=" + employeeType +
                ", worksOnProject='" + worksOnProject + '\'' +
                ", worksOnTask=" + tasks +
                '}';
    }


    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getWorksOnProject() {
        return worksOnProject;
    }

    public void setWorksOnProject(String worksOnProject) {
        this.worksOnProject = worksOnProject;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTask(List<Task> tasks) {
        this.tasks = tasks;
    }
}
