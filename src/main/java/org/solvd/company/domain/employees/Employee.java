package org.solvd.company.domain.employees;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.budget.Salary;
import org.solvd.company.domain.equipment.Laptop;
import org.solvd.company.domain.person.Person;
import org.solvd.company.domain.project.Project;
import org.solvd.company.domain.task.Task;

import java.lang.classfile.Interfaces;
import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Employee extends Person {

    private Long id;

    @XmlElement(name = "employeeType")
    private EmployeeType employeeType;

    @XmlElement(name = "salary")
    private Salary salary;

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    private List<Task> tasks;
    private List<Project> woksOnProjects;

    private Laptop laptop;

    public Employee(int age, String name, String surname, String email, LocalDate birthDay) {
        super(age, name, surname, email, birthDay);
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeType=" + employeeType +
                ", salary=" + salary +
                ", tasks=" + tasks +
                ", woksOnProjects=" + woksOnProjects +
                ", laptop=" + laptop +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && employeeType == employee.employeeType && Objects.equals(salary, employee.salary) && Objects.equals(tasks, employee.tasks) && Objects.equals(woksOnProjects, employee.woksOnProjects) && Objects.equals(laptop, employee.laptop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeType, salary, tasks, woksOnProjects, laptop);
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
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

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Project> getWoksOnProjects() {
        return woksOnProjects;
    }

    public void setWoksOnProjects(List<Project> woksOnProject) {
        this.woksOnProjects = woksOnProject;
    }

    public Employee(Builder builder) {
        super(builder.age, builder.name, builder.surname, builder.email, builder.birthDay);
        this.id = builder.id;
        this.employeeType = builder.employeeType;
        this.salary = builder.salary;
        this.tasks = builder.tasks;
        this.woksOnProjects = builder.woksOnProjects;
        this.laptop = builder.laptop;

    }

    public static class Builder {
        private Long id;
        private String name;
        private String surname;
        private Integer age;
        private String email;
        private LocalDate birthDay;
        private EmployeeType employeeType;
        private Salary salary;
        private List<Task> tasks;
        private List<Project> woksOnProjects;
        private Laptop laptop;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder birthDay(LocalDate birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public Builder employeeType(EmployeeType employeeType) {
            this.employeeType = employeeType;
            return this;
        }

        public Builder salary(Salary salary) {
            this.salary = salary;
            return this;
        }

        public Builder tasks(List<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public Builder woksOnProjects(List<Project> woksOnProjects) {
            this.woksOnProjects = woksOnProjects;
            return this;
        }

        public Builder laptop(Laptop laptop) {
            this.laptop = laptop;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
