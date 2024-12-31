package org.example.vehicles;

import org.example.interfaces.Rentable;
import org.example.user.Customer;

public class Car extends Vehicle implements Rentable{
    private double additionalChargePerDay;
    private Customer currentCustomer;


    public Car(String model, double baseRentalRate, String vehicleId, boolean isAvailable) {
        super(model, baseRentalRate, vehicleId, isAvailable);

        this.additionalChargePerDay = 10.0;
    }


    @Override
    public void start() {
        System.out.println(getModel()+ " is starting");
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("days must be greater than 0");
        }
        return (days * getbaseRentalRate()) + additionalChargePerDay;
    }

    @Override
    public boolean isAvailableForRental() {
        return getIsAvailable();
    }

    @Override
    public String toString() {
        return "Car{" +
                "additionalChargePerDay=" + additionalChargePerDay +
                ", currentCustomer=" + currentCustomer +
                '}';
    }

    public void carRentalRules() {
        System.out.println("1. Full payment before taking the car away");
        System.out.println("2. Damages will be paid by the rentee.");
        System.out.println("3. If caught for careless driving, " +
                "a fine of 1000cedis will be issued against the renter");
    }

    @Override
    public void returnVehicle() {
        if(getIsAvailable()) {
            throw new IllegalStateException("This vehicle was not been rented out.");
        }
        setIsAvailable(true);
        System.out.println(currentCustomer.getCustomerName() + " has returned vehicle.");
    }

    @Override
    public void rent(Customer customer, int days) {
        if (!isAvailableForRental()) {
            throw new IllegalArgumentException("Car is not available for rental");}
        if(days <= 0) {
            throw new IllegalArgumentException("Days must be greater than 0");
        }
            setIsAvailable(false);
            this.currentCustomer = customer;
        System.out.println("Car has been rented by "+ customer + " for " + days + " days.");
        }
    }








