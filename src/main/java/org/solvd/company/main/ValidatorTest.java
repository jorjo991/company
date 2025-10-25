package org.solvd.company.main;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class ValidatorTest {

    public static void main(String[] args) {

        File schemaFile = new File("src\\main\\resources\\company.xsd");
        File xmlFile = new File("src\\main\\resources\\company.xml");
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            System.out.println("XML is valid.");

        } catch (Exception e) {
            System.out.println("XML is NOT valid: " + e.getMessage());
        }
    }
}
