package com.nanmol.sbr.restPractice.dao;

import com.nanmol.sbr.restPractice.entities.Customer;
import com.nanmol.sbr.restPractice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DaoClass {
    @Autowired
    private CustomerService customerService;

    public Customer addCustomer(Customer c) {
        if (c.equals(customerService.save(c))) {
            return c;
        }
        return null;
    }

    public Customer readCustomer(int id) {
        Optional newCustomer = null;
        newCustomer = customerService.findById(id);
        if (newCustomer.isPresent())
            return (Customer) newCustomer.get();
        else return null;
    }

    public Customer updateCustomer(Customer c) {
        Customer foundCustomer = (Customer) customerService.findById(c.getId()).get();
        if (!foundCustomer.getName().equals(c.getName())) {
            foundCustomer.setName(c.getName());
        }
        if (!foundCustomer.getContact().equals(c.getContact())) {
            foundCustomer.setContact(c.getContact());
        }
        customerService.save(foundCustomer);
        return foundCustomer;
    }

    public Customer deleteCustomer(int id) {
        Optional newCustomer = null;
        newCustomer = customerService.findById(id);
        if (newCustomer.isPresent()) {
            customerService.deleteById(id);
            return (Customer) newCustomer.get();
        } else return null;
    }

    public Map<Integer, Customer> readAllCustomers() {
        Map<Integer, Customer> loc = new HashMap<>();
        List<Customer> customers = customerService.findAll();
        if (customers.size() == 0) {
            return null;
        } else {
            for (Customer c : customers) {
                loc.put(c.getId(), c);
            }
        }
        return loc;
    }
}
