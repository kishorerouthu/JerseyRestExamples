package com.css.jersey.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

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

    private static Invocation.Builder getInvocationBuilder(String path) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_BASE_URL);
        if (StringUtils.isNotBlank(path))
            target = target.path(path);
        return target.request(MediaType.APPLICATION_XML);
    }

}

