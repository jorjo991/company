package org.solvd.company.main;

import org.solvd.company.domain.company.Company;
import org.solvd.company.domain.employees.EmployeeType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    public static Object convert(String value, Class<?> type) {
        if (type == int.class || type == Integer.class) return Integer.parseInt(value);
        if (type == double.class || type == Double.class) return Double.parseDouble(value);
        if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(value);
        if (type == float.class || type == Float.class) return Float.parseFloat(value);
        if (type == LocalDate.class) {
            String[] values = value.split("-");
            return LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
        }
        if (type == EmployeeType.class) {
            switch (value) {
                case "JUNIOR":
                    return EmployeeType.JUNIOR;
                case "SENIOR":
                    return EmployeeType.SENIOR;
                case "MIDDLE":
                    return EmployeeType.MIDDLE;
                case "CEO":
                    return EmployeeType.CEO;
                case "MANAGER":
                    return EmployeeType.MANAGER;
            }
        }
        return value;
    }

    public static Field getFiledIncludedSuperClass(Class<?> clazz, String fieldName) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new RuntimeException("can Find in super clas");
    }

    public static Method getMethodIncludingParents(Class<?> clazz, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        while (clazz != null) {
            try {
                return clazz.getDeclaredMethod(name, parameterTypes);
            } catch (NoSuchMethodException e) {
                clazz = clazz.getSuperclass(); // go up
            }
        }
        throw new NoSuchMethodException(name);
    }

    public static Object convertToObject(Class<?> unknowCLass, Node node) {

        try {
            //get name of the class according to XML element tagName
            Class<?> clazz = Class.forName(unknowCLass.getName());
            Object object = clazz.getDeclaredConstructor().newInstance();

            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {

                Node node1 = children.item(i);
                if (node1.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node1;
                    String fieldName = element.getTagName();
                    Class<?> fieldType = getFiledIncludedSuperClass(clazz, fieldName).getType();
                    Field field = getFiledIncludedSuperClass(clazz, fieldName);

                    String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method setter = getMethodIncludingParents(clazz, setterName, fieldType);

                    if (node1.getChildNodes().getLength() == 1 && node1.getFirstChild().getNodeType() == Node.TEXT_NODE) {
                        String fieldValue = element.getTextContent();

                        Object value = convert(fieldValue, fieldType);
                        setter.invoke(object, value);
                    } else if (List.class.isAssignableFrom(fieldType)) {

                        List<Object> objectsList = new ArrayList<>();
                        ParameterizedType listType = (ParameterizedType) field.getGenericType();
                        Class<?> listObject = (Class<?>) (listType.getActualTypeArguments()[0]);
                        for (int j = 0; j < node1.getChildNodes().getLength(); j++) {
                            Node child = (node1.getChildNodes().item(j));
                            if (child.getNodeType() == Node.ELEMENT_NODE) {
                                Object object1 = convertToObject(listObject, child);
                                objectsList.add(object1);
                            }
                        }
                        setter.setAccessible(true);
                        setter.invoke(object, objectsList);
                    } else {
                        // Nested object
                        Object nestedObj = convertToObject(fieldType, node1);
                        setter.invoke(object, nestedObj);
                    }
                    System.out.println(object);
                }
            }
            return object;

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\company.xml");

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            Element root = document.getDocumentElement();

            Company company = (Company) convertToObject(Company.class, root);
            System.out.println(company);
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
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            throw new RuntimeException(e);
        }

    }
}
