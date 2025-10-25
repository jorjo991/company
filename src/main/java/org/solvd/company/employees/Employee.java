package org.solvd.company.employees;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.solvd.company.budget.Salary;
import org.solvd.company.person.Person;
import org.solvd.company.task.Task;

import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Employee extends Person {

    @XmlElement(name = "employeeType")
    private EmployeeType employeeType;

    @XmlElement(name = "worksOnProject")
    private String worksOnProject;

    @XmlElement(name = "salary")
    private Salary salary;

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    private List<Task> tasks;

    public Employee(int age, String name, String surname, String email, LocalDate birthDay) {
        super(age, name, surname, email, birthDay);
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeType=" + employeeType +
                ", worksOnProject='" + worksOnProject + '\'' +
                ", salary=" + salary +
                ", tasks=" + tasks +
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

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
