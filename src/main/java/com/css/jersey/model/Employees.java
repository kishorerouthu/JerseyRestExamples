package com.css.jersey.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kishore Routhu on 28/6/17 3:42 PM.
 */
@XmlRootElement(name = "employeeList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

    @XmlElement(name = "employee")
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        if (employeeList == null) {
            employeeList = new ArrayList<>();
        }
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
