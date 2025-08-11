package com.hdfc.policy.exceptions;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException(String message) {
        super(message);
    }
}
