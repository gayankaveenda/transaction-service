package com.tabcorp.transactionservice.service;

import com.tabcorp.transactionservice.dto.CustomerDto;
import com.tabcorp.transactionservice.mapper.CustomerDtoMapping;
import com.tabcorp.transactionservice.model.Customer;
import com.tabcorp.transactionservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapping customerDtoMapping;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerDtoMapping customerDtoMapping) {
        this.customerRepository = customerRepository;
        this.customerDtoMapping = customerDtoMapping;
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerDtoMapping::toDto)  // Using the mapping method
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customerDtoMapping.toDto(customer.get());
        }
        return null; // or throw an exception if you prefer
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerDtoMapping.toEntity(customerDto);  // Mapping DTO to entity
        customer = customerRepository.save(customer);
        return customerDtoMapping.toDto(customer);  // Mapping entity to DTO
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setFirstName(customerDto.getFirstName());
            existingCustomer.setLastName(customerDto.getLastName());
            existingCustomer.setEmail(customerDto.getEmail());
            existingCustomer.setLocation(customerDto.getLocation());

            existingCustomer = customerRepository.save(existingCustomer);
            return customerDtoMapping.toDto(existingCustomer);  // Mapping updated entity to DTO
        }
        return null; // or throw an exception if you prefer
    }
}
