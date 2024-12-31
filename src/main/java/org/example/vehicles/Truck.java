package org.example.vehicles;

import org.example.interfaces.Rentable;
import org.example.user.Customer;

public class Truck extends Vehicle implements Rentable {
    private double additionalChargePerDay;
    private Customer customer;

    // Constructor
    public Truck(String model, String vehicleId, double baseRentalRate, boolean isAvailable) {
        super(model, baseRentalRate, vehicleId, isAvailable);  // Call the parent constructor
        this.additionalChargePerDay = 30.0;  // Default additional charge
    }

    @Override
    public void rent(Customer customer, int days) {
        if (!getIsAvailable()) {
            throw new IllegalStateException("Truck is not available for rental");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than 0");
        }
        setIsAvailable(false);
        this.customer = customer;
        System.out.println("Truck rented by " + customer.getCustomerName() + " for " + days + " days.");
    }

    @Override
    public void returnVehicle() {
        if (getIsAvailable()) {
            throw new IllegalStateException("Truck is already available");
        }
        setIsAvailable(true);
        System.out.println("Truck has been returned by " + customer.getCustomerName());
        this.customer = null;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than 0");
        }
        return( getbaseRentalRate() * days) + additionalChargePerDay;  // Base rate + additional charge per day
    }

    @Override
    public boolean isAvailableForRental() {
        return getIsAvailable();  // Inherited from Vehicle
    }

    @Override
    public void start() {
        System.out.println("Truck is starting...");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Additional Charge: %.2f", additionalChargePerDay);
    }
}
