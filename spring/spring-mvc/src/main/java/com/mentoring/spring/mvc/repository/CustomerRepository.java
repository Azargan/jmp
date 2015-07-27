package com.mentoring.spring.mvc.repository;

import com.mentoring.spring.mvc.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by azargan on 22.07.15.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
