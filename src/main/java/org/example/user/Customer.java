package org.example.user;

import org.example.interfaces.LoyaltyChecker;

import java.util.ArrayList;
import java.util.List;

public class Customer implements LoyaltyChecker {
    private  String customerName;
    private  String customerId;
    private  String customerAddress;
    private  List<String> rentalHistory;
    private  List<Double> ratings = new ArrayList<>();
    private  int LoyaltyPoints;

    public Customer(String Name, String ID, String Address) {
        if (Name == null || Name == ""){
            throw new NullPointerException(" Customer Name cannot be null or empty.");
        }
        if (ID == null || ID == ""){
            throw new NullPointerException(" Customer ID cannot be null or empty.");
        }
        this.customerName = Name;
        this.customerId = ID;
        this.customerAddress = Address;
        this.rentalHistory = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerId() {
        return customerId;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public List<String> getRentalHistory() {
        return rentalHistory;
    }
    public int getLoyaltyPoints() {
        return LoyaltyPoints;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void addRental(String VehicleId){
        rentalHistory.add(VehicleId);
    }
    public boolean hasRented(String VehicleId){
        return rentalHistory.contains(VehicleId);
    }
    public void addLoyaltyPoints(int points){
        LoyaltyPoints += points;
    }
    public void addRating(double rating){
        if (rating >= 1.0 && rating <= 5.0){
            ratings.add(rating);
        }
        else {
            System.out.println("Rating must be between 1.0 and 5.0.");
        }
    }
    public double getAverageRating(){
        return ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
    public boolean rentalEligibility(){
        final int MAX_RENTAL_POINTS = 10;

        if (rentalHistory.size() > MAX_RENTAL_POINTS){
            System.out.println("Customer has reached the maximum number of concurrent rentals.");
            return false;
        }
        if (!ratings.isEmpty() && getAverageRating() < 3.0){
            System.out.println("Customer has a very low average rating so cannot rent.");
            return false;
        }
        return true;
    }
    public String toString(){
        return String.format("Customer[ID='%s', Name='%s', Rentals='%s']", customerId, customerName, rentalHistory);
    }

}
