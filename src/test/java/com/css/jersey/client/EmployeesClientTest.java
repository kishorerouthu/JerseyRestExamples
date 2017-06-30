package com.css.jersey.client;

import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.css.jersey.model.Employee;
import com.css.jersey.model.Employees;

import static org.junit.Assert.*;

/**
 * @author Kishore Routhu on 28/6/17 3:47 PM.
 */

@FixMethodOrder
@RunWith(JUnit4.class)
public class EmployeesClientTest {

    private static WebTarget target;
    private static String defaultMediaType;
    private static Client client;

    @BeforeClass
    public static void setup() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/JerseyRestExamples/rest/employees");

        defaultMediaType = MediaType.APPLICATION_XML;
    }

    @AfterClass
    public static void tearDown() {
        client.close();
    }

    @Test
    public void getAllEmployees() {
        Invocation.Builder builder = target.request(defaultMediaType);
        Response response = builder.get();

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        Employees employees = response.readEntity(Employees.class);

        assertNotNull(employees);
        assertNotNull(employees.getEmployeeList());
    }

    @Test
    public void getEmployee() {
        Invocation.Builder builder = target.path("/1").request(defaultMediaType);
        Response response = builder.get();

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        Employee employee = response.readEntity(Employee.class);

        assertNotNull(employee);
        assertEquals(employee.getId().intValue(), 1);
    }

    @Test
    public void addEmployee() {
        Employee emp = new Employee(10, "Dravid");
        Invocation.Builder builder = target.request(defaultMediaType);
        Response response = builder.post(Entity.entity(emp, defaultMediaType));

        assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());

        assertNotNull(response.getHeaderString(HttpHeaders.LOCATION));
        assertTrue(response.getHeaderString(HttpHeaders.LOCATION).contains("10"));

    }

    @Test
    public void updateEmployee() {
        Employee emp = new Employee(15, "Ganguly");
        Invocation.Builder builder = target.path("/15").request(defaultMediaType);
        Response response = builder.put(Entity.entity(emp, defaultMediaType));

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        Employee employee = response.readEntity(Employee.class);
        assertNotNull(employee);
        assertEquals(employee.getId(), emp.getId());
        assertEquals(employee.getName(), emp.getName());
    }

    @Test
    public void deleteEmployee() {
        Invocation.Builder builder = target.path("/1").request(defaultMediaType);
        Response response = builder.delete();

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertTrue(response.readEntity(String.class).contains("1"));
    }
}

