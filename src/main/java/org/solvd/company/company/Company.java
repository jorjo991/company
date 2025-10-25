package org.solvd.company.company;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;
import org.solvd.company.budget.Budget;
import org.solvd.company.client.Client;
import org.solvd.company.department.Department;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "Company")
@XmlRootElement(name = "Company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "address")
    private Address address;

    @XmlElement(name = "budget")
    private Budget budget;

    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    private List<Client> clients;

    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    private List<Department> departments;

    public Company(String name, Address address, Budget budget) {

        this.name = name;
        this.address = address;
        this.departments = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.budget = budget;
    }

    public Company() {

    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", budget=" + budget +
                ", clients=" + clients +
                ", departments=" + departments +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}
