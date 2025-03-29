package com.tabcorp.transactionservice.repository;

import com.tabcorp.transactionservice.dto.TotalCostPerCustomerDto;
import com.tabcorp.transactionservice.dto.TotalCostPerProductDto;
import com.tabcorp.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT new com.tabcorp.transactionservice.dto.TotalCostPerProductDto(t.productCode, SUM(t.quantity * p.cost)) " +
            "FROM Transaction t " +
            "JOIN Product p ON t.productCode = p.productCode " +
            "GROUP BY t.productCode")
    List<TotalCostPerProductDto> getTotalCostPerProduct();

    @Query("SELECT COUNT(t.id) " +
            "FROM Transaction t " +
            "JOIN Customer c ON t.customerId = c.id " +
            "WHERE c.location = :country")
    Long getNumberOfTransactionsForCustomersByCountry(@Param("country") String country);

}
