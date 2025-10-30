package org.solvd.company.client;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.solvd.company.person.Person;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Client extends Person {

    private Long id;
    @XmlElement(name = "active")
    private Boolean active;

    public Client(int age, String name, String surname, String email, LocalDate birthday, Boolean active, Long id) {
        super(age, name, surname, email, birthday);
        this.active = active;
        this.id = id;
    }

    public Client() {

    }

    @Override
    public String toString() {
        return super.toString() + "Client{" +
                "id=" + id +
                ", active=" + active +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
