package org.charter.democharter.mapper.entity;

import lombok.NonNull;
import org.charter.democharter.business.model.Customer;
import org.charter.democharter.entity.CustomerEntity;
import org.charter.democharter.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper implements BaseMapper<Customer, CustomerEntity> {

    @Override
    public CustomerEntity map(@NonNull Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerID(customer.getCustomerID());
        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setZipCode(customer.getZipCode());
        return customerEntity;
    }

}
