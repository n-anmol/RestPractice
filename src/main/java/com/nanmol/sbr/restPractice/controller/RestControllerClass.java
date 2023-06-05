package com.nanmol.sbr.restPractice.controller;

import com.nanmol.sbr.restPractice.dao.DaoClass;
import com.nanmol.sbr.restPractice.entities.Customer;
import com.nanmol.sbr.restPractice.exceptions.CustomerErrorResponse;
import com.nanmol.sbr.restPractice.exceptions.CustomerNotFoundException;
import com.nanmol.sbr.restPractice.exceptions.NoCustomersException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer CRUD API", description = "API endpoints to perform operation(s) on Customer entities")
public class RestControllerClass {
    @Autowired
    private DaoClass dao;

    @PostMapping("/create")
    @Operation(summary = "Create a new customer with the request body")
    public Customer createCustomer(@RequestBody Customer c) {

        return dao.addCustomer(c);
    }

    @GetMapping("/read/{id}")
    @Operation(summary = "Read a customer with provided customer ID")
    public Customer readCustomer(@PathVariable int id) {
        Customer foundCustomer = dao.readCustomer(id);
        if (foundCustomer != null) {
            return foundCustomer;
        } else {
            throw new CustomerNotFoundException("Customer with ID: " + id + " is not in the database !");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a customer with provided customer ID and request body")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer c) {
        Customer newCustomer = dao.readCustomer(id);
        if (newCustomer != null) {
            c.setId(id);
            return dao.updateCustomer(c);
        } else {
            throw new CustomerNotFoundException("Customer with ID: " + id + " is not in the database !");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a customer with provided customer ID")
    public Customer deleteCustomer(@PathVariable int id) {
        Customer deletedCustomer = dao.deleteCustomer(id);
        if (deletedCustomer != null) {
            return deletedCustomer;
        } else {
            throw new CustomerNotFoundException("Customer with ID: " + id + " is not in the database !");
        }
    }

    @GetMapping("/readAll")
    @Operation(summary = "Read all customers in the database")
    public Map<Integer, Customer> readAllCustomers(){
        Map<Integer, Customer> loc= dao.readAllCustomers();
        if(loc==null){
            throw new NoCustomersException("No customers in database to display!");
        }else{
            return loc;
        }
    }
    @ExceptionHandler(value =  NoCustomersException.class)
    public ResponseEntity<CustomerErrorResponse> handleNoCustomers(NoCustomersException ex){
        CustomerErrorResponse cer = new CustomerErrorResponse();
        cer.setStatus(HttpStatus.BAD_REQUEST.value());
        cer.setMsg(ex.getMessage());
        cer.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<CustomerErrorResponse>(cer, HttpStatus.BAD_REQUEST);
    }
}
