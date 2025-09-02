package com.reward_project.credit.exception;

public class CreditNotExistException extends RuntimeException{
    public CreditNotExistException(String message) {
        super(message);
    }
}
