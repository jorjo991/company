package org.solvd.company.main;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.solvd.company.company.Company;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

    public static void documentIterator(Node node) {

        if (node.getNodeType() == Node.DOCUMENT_NODE || node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println("Element " + node.getNodeName());

        }
        if (node.getNodeType() == Node.TEXT_NODE) {
            String text = node.getTextContent().trim();
            if (!text.isEmpty()) {
                System.out.println("  Text: " + text);
            }
        }
        if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
            System.out.println(node.getLocalName() + " " + node.getNodeValue());
        }
        NodeList childrenNodes = node.getChildNodes();
        for (int i = 0; i < childrenNodes.getLength(); i++) {
            documentIterator(childrenNodes.item(i));
        }
    }

    public static void main(String[] args) throws SAXException {

        File schemaFile = new File("src\\main\\resources\\company.xsd");
        File xmlFile = new File("src\\main\\resources\\company.xml");

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema = factory.newSchema(schemaFile);
        System.out.println(schema.toString());
        Validator validator = schema.newValidator();
        // reading from DOM
        try {
            validator.validate(new StreamSource(xmlFile));
            System.out.println("XML is valid.");
        } catch (Exception e) {
            System.out.println("XML is NOT valid: " + e.getMessage());
        }
        System.out.println();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            //iterate through DOM file
            documentIterator(document.getDocumentElement());

            System.out.println("--------------------------------------------------------");
            XPath xPath = XPathFactory.newInstance().newXPath();
            String[] paths = {
                    "/Company/name",
                    "/Company/Address/city",
                    "/Company/Departments/Department/Employees/Employee/age",
                    "/Company/Departments/Department/Employees/Employee/name",
                    "/Company/Departments/Department/Employees/Employee/email"
            };

            for (String path : paths) {
                NodeList nodeList = (NodeList) xPath.compile(path).evaluate(document, XPathConstants.NODESET);
                System.out.println("XPath: " + path + " Length " + nodeList.getLength());
                for (int j = 0; j < nodeList.getLength(); j++) {
                    System.out.println(nodeList.item(j).getNodeName() + " : " + nodeList.item(j).getTextContent());
                }
                System.out.println();
            }
        } catch (ParserConfigurationException | IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }

        //creating complete hierarchy using JAXB
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Company.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Company company = (Company) unmarshaller.unmarshal(xmlFile);

            System.out.println(company.toString());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("JSON");
        //Parse Json with jackson
        File jsonFile = new File("src\\main\\resources\\Company.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Company company = objectMapper.readValue(jsonFile, Company.class);
            System.out.println(company);

            JsonNode jsonNode = objectMapper.readTree(jsonFile);

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

        // here is two example of StAX API using StreamReader and IteratorReader (EventReader)
        /*
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            XMLStreamReader xmlReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(xmlFile.getPath()));
            XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlFile.getPath()));

            while (xmlReader.hasNext()) {
                int even = xmlReader.next();
                switch (even) {
                    case XMLStreamReader.START_ELEMENT -> {
                        for (int i = 0; i < xmlReader.getAttributeCount(); i++) {
                            System.out.println(xmlReader.getAttributeLocalName(i) + " " + xmlReader.getAttributeValue(i));
                        }

                        System.out.println("Start Element " + xmlReader.getName());
                    }
                    case XMLStreamReader.END_ELEMENT -> System.out.println("End element " + xmlReader.getName());
                    case XMLStreamConstants.CHARACTERS -> System.out.println(xmlReader.getText());
                }

            }

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) System.out.println("Start element" + event.asStartElement());
                if (event.isCharacters()) System.out.println(event.asCharacters());
                if (event.isEndElement()) System.out.println("End element " + event.asEndElement());
            }

        } catch (FileNotFoundException | XMLStreamException fileNotFoundException) {
            throw new RuntimeException();
        }
         */
    }
}