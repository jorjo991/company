package org.solvd.company.main;

import org.solvd.company.domain.company.Company;
import org.solvd.company.persistence.impl.ClientsRepositoryImp;
import org.solvd.company.persistence.impl.CompanyRepositoryImp;
import org.solvd.company.persistence.impl.LaptopRepositoryImp;
import org.solvd.company.persistence.impl.TaskRepositoryImp;
import org.xml.sax.SAXException;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SAXException {
        try {

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/company", "postgres", "evashechema123");

            CompanyRepositoryImp companyRepositoryImp = new CompanyRepositoryImp();
            System.out.println(companyRepositoryImp.readAll());

            /*
            TaskRepositoryImp taskRepositoryImp = new TaskRepositoryImp();
            System.out.println(taskRepositoryImp.get(1L));
            System.out.println(taskRepositoryImp.readAll());
            LaptopRepositoryImp laptopRepositoryImp = new LaptopRepositoryImp();
            System.out.println(laptopRepositoryImp.get(1L));
            System.out.println(laptopRepositoryImp.readAll());

            ClientsRepositoryImp clientsRepositoryImp = new ClientsRepositoryImp();
            System.out.println(clientsRepositoryImp.get(2L));
            System.out.println(clientsRepositoryImp.readAll());

             */
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}