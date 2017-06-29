package com.css.jersey.client;

import java.util.List;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.css.jersey.model.Employee;
import com.css.jersey.model.Employees;

/**
 * @author Kishore Routhu on 28/6/17 3:47 PM.
 */
public class EmployeesClient {


    private static final String API_BASE_URL = "http://localhost:8080/JerseyRestExamples/rest/employees";

    public static void main(String[] args) {
        getAllEmployees();
        getEmployee();
        addEmployee();
        updateEmployee();
    }

    private static void getAllEmployees() {
        Invocation.Builder builder = getInvocationBuilder("");
        Response response = builder.get();
        Employees employees = response.readEntity(Employees.class);
        List<Employee> employeeList = employees.getEmployeeList();

        System.out.println("************************");
        System.out.println("ALL EMPLOYEES :: " + response.getStatus());
        System.out.println(employeeList);
        System.out.printf("************************");
    }

    private static void getEmployee() {
        Invocation.Builder builder = getInvocationBuilder("/1");
        Response response = builder.get();
        Employee employee = response.readEntity(Employee.class);

        System.out.println("************************");
        System.out.println("ONE EMPLOYEE :: " + response.getStatus());
        System.out.println(employee);
        System.out.printf("************************");

    }

    private static void addEmployee() {
        Employee emp = new Employee(10, "Dravid");
        Invocation.Builder builder = getInvocationBuilder("");
        Response response = builder.post(Entity.entity(emp, MediaType.APPLICATION_XML_TYPE));
        System.out.println("************************");
        System.out.println("ADD EMPLOYEE :: " + response.getStatus());
        System.out.println(response.readEntity(String.class));
        System.out.printf("************************");
    }

    private static void updateEmployee() {
        Employee emp = new Employee();
        emp.setName("Ganguly");
        Invocation.Builder builder = getInvocationBuilder("/15");
        Response response = builder.put(Entity.entity(emp, MediaType.APPLICATION_XML_TYPE));

        System.out.println("************************");
        System.out.println("UPDATE EMPLOYEE :: " + response.getStatus());
        System.out.println(response.readEntity(Employee.class));
        System.out.printf("************************");
    }

    private static Invocation.Builder getInvocationBuilder(String path) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_BASE_URL);
        if (StringUtils.isNotBlank(path))
            target = target.path(path);
        return target.request(MediaType.APPLICATION_XML);
    }

}

