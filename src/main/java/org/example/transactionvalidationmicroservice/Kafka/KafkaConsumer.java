package org.example.transactionvalidationmicroservice.Kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.transactionvalidationmicroservice.model.Transaction;
import org.example.transactionvalidationmicroservice.service.TransactionProcessor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final TransactionProcessor processor;

    @KafkaListener(topics = "t1_demo_transaction_accept", groupId = "transaction-service")
    public void listen(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Transaction tx = objectMapper.readValue(message, Transaction.class);
        processor.processTransaction(tx);
    }


}
