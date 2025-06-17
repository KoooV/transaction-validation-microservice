package org.example.transactionvalidationmicroservice.service;

import lombok.AllArgsConstructor;
import org.example.transactionvalidationmicroservice.repository.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BlackListService {

    private BlackListRepository blackListRepository;

    public boolean isBlacklisted(UUID accountId) {
        return blackListRepository.checkClientBlackList(accountId);
    }
} 