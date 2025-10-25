package org.solvd.company.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.solvd.company.company.Company;

import java.io.File;
import java.io.IOException;

public class JSONParser {

    public static void main(String[] args) {

        File file = new File("src\\main\\resources\\Company.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            Company company = objectMapper.readValue(file, Company.class);
            System.out.println(company);

            JsonNode jsonNode = objectMapper.readTree(file);

            String companyName = jsonNode.at("/name").asText();
            String companyInCity = jsonNode.at("/address/city").asText();
            String companyManager = jsonNode.at("/departments/0/employees/4/name").asText();
            String juniorDeveloperTaskName = jsonNode.at("/departments/0/employees/0/tasks/0/name").asText();
            String juniorDeveloperWorksOnProject = jsonNode.at("/departments/0/employees/0/worksOnProject").asText();

            System.out.println("Company Name " + companyName);
            System.out.println("Company Office is in " + companyInCity);
            System.out.println("Company Manager name " + companyManager);
            System.out.println("Junior developer works  task " + juniorDeveloperTaskName);
            System.out.println("Junior developer works on Projects " + juniorDeveloperWorksOnProject);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
