package org.example.transactionvalidationmicroservice.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String accountId;
    private String clientId;
    private String transactionId;
    private BigDecimal balance;
    private BigDecimal amount;
    private long timestamp;




}
