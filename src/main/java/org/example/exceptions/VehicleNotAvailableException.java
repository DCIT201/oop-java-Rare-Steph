package org.example.exceptions;

// Moved this class to the exception package, since it's suppposed to handle exceptions and as such must be in its respective class
public class VehicleNotAvailableException extends Exception {
    public VehicleNotAvailableException(String message) {
        super(message);
    }
}
