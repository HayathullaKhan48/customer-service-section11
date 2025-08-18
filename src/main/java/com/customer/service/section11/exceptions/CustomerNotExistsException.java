package com.customer.service.section11.exceptions;

/**
 * Customer exception when customer is not found and the constructor pass customer error message
 */
public class CustomerNotExistsException extends RuntimeException {
    public CustomerNotExistsException() {
    }

    public CustomerNotExistsException(String message) {
        super(message);
    }
}
