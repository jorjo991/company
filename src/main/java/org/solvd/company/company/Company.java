package org.solvd.company.company;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;
import org.solvd.company.budget.Budget;
import org.solvd.company.client.Client;
import org.solvd.company.department.Department;
import org.solvd.company.office.Office;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "Company")
@XmlRootElement(name = "Company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

    private Long id;
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "budget")
    private Budget budget;

    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    private List<Client> clients;

    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    private List<Department> departments;

    private List<Office> offices;

    public Company(Long id, String name, Address address, Budget budget) {
        this.id = id;
        this.name = name;
        this.departments = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.budget = budget;
    }

    public Company() {

    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", clients=" + clients +
                ", departments=" + departments +
                ", offices=" + offices +
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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }
}
