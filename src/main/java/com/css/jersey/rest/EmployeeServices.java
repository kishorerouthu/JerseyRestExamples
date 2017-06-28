package com.css.jersey.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.css.jersey.model.Employee;
import com.css.jersey.model.Employees;

/**
 * @author Kishore Routhu on 28/6/17 3:44 PM.
 */
@Path("/employees")
public class EmployeeServices {


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public Employees getAllEmployees() {
        Employees employees = new Employees();
        employees.getEmployeeList().add(new Employee(1, "Sachin"));
        employees.getEmployeeList().add(new Employee(2, "Dhoni"));
        employees.getEmployeeList().add(new Employee(3, "Rohit"));
        return employees;
    }

    @GET
    @Path("/{id}")
    public Response getEmployee(@PathParam("id") Integer id) {
        if (id < 0)
            return Response.noContent().build();

        Employee employee = new Employee(id, "Kishore Routhu");
        GenericEntity<Employee> entity = new GenericEntity<>(employee, Employee.class);
        return Response.ok().entity(entity).build();
    }
}
