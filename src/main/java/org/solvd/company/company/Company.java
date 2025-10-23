package org.solvd.company.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;
import org.solvd.company.department.Department;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "Company")
@XmlRootElement(name = "Company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "Address")
    private Address address;
    @XmlElementWrapper(name = "Departments")
    @XmlElement(name = "Department")
    private List<Department> departments;

    public Company(String name, Address address) {
        this.name = name;
        this.address = address;
        this.departments = new ArrayList<>();

    }

    public Company() {

    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address=" + address +
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

}
