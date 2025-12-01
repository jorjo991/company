package org.solvd.company.designPatterns.facade;

import org.solvd.company.domain.client.Client;

import java.time.LocalDate;

public class ClientService {

    private Client client;

    public void createClient(String name, String email, String surname, LocalDate birthDay) {
        this.client = new Client();
        this.client.setName(name);
        this.client.setEmail(email);
        this.client.setSurname(surname);
        this.client.setBirthday(birthDay);

    }
}
