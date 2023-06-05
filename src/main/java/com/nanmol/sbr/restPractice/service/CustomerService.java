package com.nanmol.sbr.restPractice.service;

import com.nanmol.sbr.restPractice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerService extends JpaRepository<Customer, Integer> {
}
