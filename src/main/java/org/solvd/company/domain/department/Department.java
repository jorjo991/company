package org.solvd.company.domain.department;

import jakarta.xml.bind.annotation.*;
import org.solvd.company.domain.budget.Budget;
import org.solvd.company.domain.employees.Employee;
import org.solvd.company.domain.office.Room;
import org.solvd.company.domain.project.Project;

import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Department {

    @XmlAttribute(name = "id")
    private Long id;

    private String name;

    @XmlElement(name = "number")
    private String departmentNumber;

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private List<Project> projects;

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
                ", departmentNumber='" + departmentNumber + '\'' +
                ", employees=" + employees +
                ", projects=" + projects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(departmentNumber, that.departmentNumber) && Objects.equals(employees, that.employees) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departmentNumber, employees, projects);
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

}
