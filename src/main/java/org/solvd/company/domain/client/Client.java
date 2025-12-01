package org.solvd.company.domain.client;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.solvd.company.designPatterns.observer.ClientObserver;
import org.solvd.company.domain.person.Person;

import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Client extends Person implements ClientObserver {

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(active, client.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active);
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void update(String message) {

        System.out.println("Client " + this.getName() + " received update: " + message);

    }
}
