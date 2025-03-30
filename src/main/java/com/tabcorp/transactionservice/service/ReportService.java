package com.tabcorp.transactionservice.service;

import com.tabcorp.transactionservice.dto.TotalCostPerCustomerDto;
import com.tabcorp.transactionservice.dto.TotalCostPerProductDto;
import com.tabcorp.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public ReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TotalCostPerCustomerDto> getTotalCostPerCustomer() {
        return transactionRepository.getTotalCostPerCustomer();
    }

    public List<TotalCostPerProductDto> getTotalCostPerProduct() {
        return transactionRepository.getTotalCostPerProduct();
    }

    public Long getNumberOfTransactionsForCustomersByCountry(String country) {
        return transactionRepository.getNumberOfTransactionsForCustomersByCountry(country);
    }
}
