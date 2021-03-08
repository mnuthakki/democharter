package org.charter.democharter.service;

import lombok.extern.log4j.Log4j2;
import org.charter.democharter.business.model.Customer;
import org.charter.democharter.entity.CustomerEntity;
import org.charter.democharter.mapper.entity.CustomerEntityMapper;
import org.charter.democharter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerEntityMapper customerEntityMapper;

    public Customer getCustomerByID(int id) {
        Customer customer;
        try {
            customer = new Customer();
            Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
            if (customerEntity.isPresent()) {
                customer.setCustomerID(customerEntity.get().getCustomerID());
                customer.setFirstName(customerEntity.get().getFirstName());
                customer.setLastName(customerEntity.get().getLastName());
                customer.setZipCode(customerEntity.get().getZipCode());
            }
        } catch (Exception e) {
            log.error("Exception occurred retrieving the customer information for ID: " + id + " with message : " + e.getMessage());
            throw e;
        }
        return customer;
    }

}
