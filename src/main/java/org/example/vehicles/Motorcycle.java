package org.example.vehicles;

import org.example.interfaces.Rentable;
import org.example.user.Customer;


public class Motorcycle extends Vehicle implements Rentable {
    private Customer customer;
    private double additionalRentalRates;

    public Motorcycle(String model, double baseRentalRates, String vehicleId, boolean isAvailable) {
        super(model, baseRentalRates, vehicleId, isAvailable);
        this.additionalRentalRates = 15.0;
    }

    public double getAdditionalRentalRates() {
        return additionalRentalRates;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void start() {
        System.out.println("Your " + getModel() + " motorcycle is starting..");
    }

    public void stop() {
        System.out.println("Your motorcycle is stopping..");
    }

    public void sprint() {
        System.out.println("Are you ready?");
        System.out.println("Your motorcycle is sprinting...");
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("days must be greater than zero");
        }
        return (getbaseRentalRate() * days) + additionalRentalRates;
    }

    @Override
    public boolean isAvailableForRental() {
        return getIsAvailable();
    }

    @Override
    public void returnVehicle() {
        if (getIsAvailable()) {
            throw new IllegalArgumentException("Motorcycle is available for rental.");
        }
        setIsAvailable(true);
        System.out.println(getModel() + " is  being returned by " + customer.getCustomerName());
        this.customer = null;
    }

    @Override
    public void rent(Customer customer, int days) {
        if (getIsAvailable()) {
            throw new IllegalArgumentException("Motorcycle is not available for rental.");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("days must be greater than zero");
        }
        setIsAvailable(false);
        this.customer = customer;
        System.out.println(getModel() + " is rented by " + customer.getCustomerName() + " for " + days + " days.");
    }

    public void motorcycleRentalRules() {
        System.out.println("1. Fine will be paid if rented for more than 5 hours.");
        System.out.println("2. Damages and scratches are repayable and refundable.");
        System.out.println("3. Full payment must ne paid before check out.");
    }
}

