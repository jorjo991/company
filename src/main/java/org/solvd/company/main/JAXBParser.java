package org.solvd.company.main;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.solvd.company.company.Company;

import java.io.File;

public class JAXBParser {

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\company.xml");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Company company = (Company) unmarshaller.unmarshal(file);
            System.out.println(company);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
