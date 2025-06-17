package org.example.transactionvalidationmicroservice.controller;

import lombok.AllArgsConstructor;
import org.example.transactionvalidationmicroservice.model.BlackList;
import org.example.transactionvalidationmicroservice.service.BlackListService;
import org.example.transactionvalidationmicroservice.service.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/blacklist")
@AllArgsConstructor
public class BlackListController {

    private final BlackListService blackListService;

    @GetMapping("/check/{accountId}")
    public ResponseEntity<ResponseMessage> checkClient(@PathVariable UUID accountId) {
        boolean isBlacklisted = blackListService.isBlacklisted(accountId);
        String message;
        BlackList.Status status;
        if (isBlacklisted) {
            message = "Клиент в черном списке";
            status = BlackList.Status.BLACKLIST;
        } else {
            message = "Клиент не в черном списке";
            status = BlackList.Status.OK;
        }
        return ResponseEntity.ok(new ResponseMessage(message, status));
    }
} 