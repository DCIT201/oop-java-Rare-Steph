package org.example.exceptions;

public class InvalidRentalDurationException extends Exception{
    public InvalidRentalDurationException(String message) {
        super(message);
    }
}
