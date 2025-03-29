package com.tabcorp.transactionservice.service;

import com.tabcorp.transactionservice.dto.TotalCostPerCustomerDto;
import com.tabcorp.transactionservice.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    private final TransactionRepository transactionRepository;

    @Autowired
    public ReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TotalCostPerCustomerDto> getTotalCostPerCustomer() {
        // Now we can directly return the result from the repository
        return transactionRepository.getTotalCostPerCustomer();
    }
}
