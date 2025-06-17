package org.example.transactionvalidationmicroservice.controller;

import org.example.transactionvalidationmicroservice.model.BlackList;

public class ResponseMessage {
    private String message;
    private BlackList.Status status;
    public ResponseMessage(String message, BlackList.Status status) {
        this.message = message;
        this.status = status;
    }
}
