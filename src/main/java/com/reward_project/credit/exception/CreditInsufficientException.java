package com.reward_project.credit.exception;

public class CreditInsufficientException extends  RuntimeException{
    public CreditInsufficientException(String message) {
        super(message);
    }
}
