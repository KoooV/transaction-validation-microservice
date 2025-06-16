package org.example.transactionvalidationmicroservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.transactionvalidationmicroservice.model.Transaction;
import org.example.transactionvalidationmicroservice.model.TransactionResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class TransactionProcessor {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${transaction.threshold-count}")
    private int thresholdNum;

    @Value("${transaction.threshold-time-seconds}")
    private long thresholdTime;

    private final Map<String, Deque<Transaction>> recentTransactions = new ConcurrentHashMap<>();//хранение транзакций в памяти

    public void processTransaction(Transaction tx) throws JsonProcessingException {
        String key = tx.getClientId() + "_" + tx.getAccountId();

        Deque<Transaction> deque = recentTransactions.get(key);
        if (deque == null) {
            deque = new LinkedList<>();
            recentTransactions.put(key, deque);
        }

        long now = tx.getTimestamp();

        // Удаляем старые транзакции
        Iterator<Transaction> iterator = deque.iterator();
        while (iterator.hasNext()) {
            Transaction t = iterator.next();
            if (now - t.getTimestamp() > thresholdTime) {
                iterator.remove();
            }
        }

        // Добавляем новую транзакцию
        deque.addLast(tx);

        // Проверка баланса
        if (tx.getAmount().compareTo(tx.getBalance()) > 0) {
            sendResult(new TransactionResult(tx.getTransactionId(), tx.getAccountId(), "REJECTED"));
            return;
        }

        // Проверка количества транзакций
        if (deque.size() > thresholdNum) {
            for (Transaction t : deque) {
                sendResult(new TransactionResult(t.getTransactionId(), t.getAccountId(), "BLOCKED"));
            }
            deque.clear(); // Очистить очередь после блокировки
        } else {
            sendResult(new TransactionResult(tx.getTransactionId(), tx.getAccountId(), "ACCEPTED"));
        }
    }

    private void sendResult(TransactionResult result) throws JsonProcessingException {
        String msg = objectMapper.writeValueAsString(result);
        kafkaTemplate.send("t1_demo_transaction_result", msg);

    }
}
