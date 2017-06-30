package com.css.jersey.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.*;
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
    @Produces(MediaType.APPLICATION_XML)
    public Response getEmployee(@PathParam("id") Integer id) {
        if (id < 0)
            return Response.noContent().build();

        Employee employee = new Employee(id, "Kishore Routhu");
        GenericEntity<Employee> entity = new GenericEntity<>(employee, Employee.class);
        return Response.ok().entity(entity).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response addEmployee(Employee emp) throws URISyntaxException {

        if (emp == null)
            return Response.status(400).entity("Please add employee details...!").build();

        if (emp.getName() == null)
            return Response.status(400).entity("Please provide the employee name...!").build();

        return Response.created(new URI("/rest/employees/" + emp.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateEmployee(@PathParam("id") Integer empId, Employee emp) {

        if (emp == null)
            return Response.status(400).entity("Please update employee details...!").build();

        if (emp.getName() == null)
            return Response.status(400).entity("Please provide the employee name...!").build();

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(empId);
        updatedEmployee.setName(emp.getName());

        return Response.ok().entity(updatedEmployee).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteEmployee(@PathParam("id") int id) {
        return Response.ok().entity("Employee deleted successfully ...! with " + id).build();
    }
}
