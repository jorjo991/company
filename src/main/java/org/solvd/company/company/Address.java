package org.solvd.company.company;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    private Long id;
    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "street")
    private String street;

    @XmlElement(name = "number")
    private Integer number;

    public Address() {
    }

    public Address(String city, String street, Integer number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
