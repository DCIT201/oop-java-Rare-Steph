package org.example.vehicles;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public abstract class Vehicle {
    private final String model;
    private  String vehicleId;
    private  double baseRentalRate;
    private boolean isAvailable;
    private List<Double> ratings = new ArrayList<>();

    public Vehicle(String model, double baseRentalRate, String vehicleId, boolean isAvailable) {
        if (vehicleId == null || vehicleId.isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be null or empty");
        }
        if (model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        if (baseRentalRate <= 0){
            throw new IllegalArgumentException("Base Rental Rate must be greater than 0");
        }
        this.model = model;
        this.vehicleId = vehicleId;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true;
    }

    public String getModel() {return model;}
    public double getbaseRentalRate() {return baseRentalRate;}
    public String getVehicleId() {return vehicleId;}
    public boolean getIsAvailable() {return isAvailable;}

    public void setOwner(String owner) {
        System.out.println("The owner is: " + owner);
    }
    public void setModel(String model) {
        System.out.println("The model is: " + model);
    }
    public void setIsAvailable(boolean Available) {
        isAvailable = Available;
    }

    public abstract void start();
    public abstract double calculateRentalCost(int days);
    public abstract boolean isAvailableForRental();

    public void addRating(double rating) {
        if (rating >= 1.0 && rating <= 5.0) {
            ratings.add(rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }
    }

    public double getAverageRating() {
        return
        ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public String toString(){
        return String.format("Vehicle ID: %s, Model: %s, Base Rate: %2f, Avg. Rating: %2f"
                ,vehicleId,model,baseRentalRate,ratings);
        }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
    Vehicle vehicle = (Vehicle) obj;
    return vehicleId.equals(vehicle.vehicleId);}

    public int hashCode() {
        return Objects.hashCode(vehicleId);
    }

}





