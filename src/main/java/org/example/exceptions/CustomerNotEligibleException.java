package org.example.exceptions;

// Added the extends Exception to the code since you will be using the Exception class to handle exceptions.
public class CustomerNotEligibleException extends Exception {
    public CustomerNotEligibleException(String message) {
        super(message);
    }
}
