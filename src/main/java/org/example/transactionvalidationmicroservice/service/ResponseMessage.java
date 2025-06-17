package org.example.transactionvalidationmicroservice.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.transactionvalidationmicroservice.model.BlackList;

@AllArgsConstructor
@Getter
@Setter
public class ResponseMessage {
    private String responseMessage;
    private BlackList.Status status;
} 