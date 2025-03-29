package com.tabcorp.transactionservice.repository;

import com.tabcorp.transactionservice.dto.TotalCostPerCustomerDto;
import com.tabcorp.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Directly map the result to a DTO
    @Query("SELECT new com.tabcorp.transactionservice.dto.TotalCostPerCustomerDto(t.customerId, SUM(t.quantity * p.cost)) " +
            "FROM Transaction t " +
            "JOIN Product p ON t.productCode = p.productCode " +
            "GROUP BY t.customerId")
    List<TotalCostPerCustomerDto> getTotalCostPerCustomer();
}
