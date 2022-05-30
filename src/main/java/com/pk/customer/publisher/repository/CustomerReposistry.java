package com.pk.customer.publisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pk.customer.publisher.model.Customer;

@Repository("customerReposistry")
public interface CustomerReposistry extends JpaRepository<Customer, Integer> {

}
