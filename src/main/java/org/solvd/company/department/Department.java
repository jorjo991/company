package org.solvd.company.department;

import jakarta.xml.bind.annotation.*;
import org.solvd.company.budget.Budget;
import org.solvd.company.employees.Employee;
import org.solvd.company.office.Room;
import org.solvd.company.project.Project;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Department {

    @XmlAttribute(name = "id")
    private Long id;

    private String name;

    @XmlElement(name = "budget")
    private Budget budget;

    @XmlElement(name = "number")
    private String departmentNumber;

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private List<Project> projects;
    private List<Room> availableRooms;

    public Department(Long id, String name, String departmentNumber, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.departmentNumber = departmentNumber;
        this.employees = employees;
    }

    public Department() {

    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", departmentNumber='" + departmentNumber + '\'' +
                ", employees=" + employees +
                ", projects=" + projects +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Room> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(List<Room> availableRooms) {
        this.availableRooms = availableRooms;
    }
}
