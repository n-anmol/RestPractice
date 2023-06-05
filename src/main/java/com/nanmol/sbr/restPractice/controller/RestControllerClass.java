package com.nanmol.sbr.restPractice.controller;

import com.nanmol.sbr.restPractice.dao.DaoClass;
import com.nanmol.sbr.restPractice.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/customer")
public class RestControllerClass {
    @Autowired
    private DaoClass dao;

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer c){
        Customer newCustomer = dao.addCustomer(c);
        return newCustomer;
    }
    @GetMapping("/read/{id}")
    public Customer readCustomer(@PathVariable int id){
        Customer newCustomer = dao.readCustomer(id);
        return newCustomer;
    }

    @PostMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer c){
        Customer newCustomer = dao.readCustomer(id);
        if(newCustomer!=null){
            c.setId(id);
            return dao.updateCustomer(c);
        }
        else return null;
    }


}
