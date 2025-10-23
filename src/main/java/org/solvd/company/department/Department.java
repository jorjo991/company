package org.solvd.company.department;

import jakarta.xml.bind.annotation.*;
import org.solvd.company.employees.Employee;
import org.solvd.company.project.Project;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Department {
    @XmlAttribute(name = "id")
    private int id;
    private String name;
    @XmlElement(name = "number")
    private String departmentNumber;
    @XmlElementWrapper(name = "Employees")
    @XmlElement(name = "Employee")
    private List<Employee> employees;
    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "Project")
    private List<Project> projects;

    public Department(int id, String name, String departmentNumber, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.departmentNumber = departmentNumber;
        this.employees = employees;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department() {

    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentNumber='" + departmentNumber + '\'' +
                ", employees=" + employees +
                '}';
    }

    public int getId() {
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

    public void setId(int id) {
        this.id = id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
