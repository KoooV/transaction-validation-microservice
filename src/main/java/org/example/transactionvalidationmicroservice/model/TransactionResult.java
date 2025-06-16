package org.example.transactionvalidationmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResult {
        private String transactionId;
        private String accountId;
        private String status;

}
