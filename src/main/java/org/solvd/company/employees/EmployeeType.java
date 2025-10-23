package org.solvd.company.employees;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "EmployeeType")
@XmlEnum
public enum EmployeeType {
    @XmlEnumValue("MANAGER")
    MANAGER,

    @XmlEnumValue("CEO")
    CEO,

    @XmlEnumValue("JUNIOR")
    JUNIOR,

    @XmlEnumValue("MIDDLE")
    MIDDLE,

    @XmlEnumValue("SENIOR")
    SENIOR;
}
