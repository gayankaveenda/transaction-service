package com.tabcorp.transactionservice.mapper;

import com.tabcorp.transactionservice.dto.CustomerDto;
import com.tabcorp.transactionservice.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapping {

    // Mapping Customer entity to CustomerDto
    public CustomerDto toDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setLocation(customer.getLocation());
        return customerDto;
    }

    // Mapping CustomerDto to Customer entity
    public Customer toEntity(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setLocation(customerDto.getLocation());
        return customer;
    }
}

