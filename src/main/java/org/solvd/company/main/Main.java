package org.solvd.company.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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


    public static void main(String[] args) throws SAXException {
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