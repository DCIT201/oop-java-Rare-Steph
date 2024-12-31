package org.example.agency;

import org.example.vehicles.Vehicle;


public class RentalTransaction {
    private String CustomerName;
    private Vehicle vehicle;
    private int RentalDays;
    private String modeOfPayment;

    public RentalTransaction(String customerName, Vehicle vehicle,int  rentDay, String payVia) {
        this.CustomerName = customerName;
        this.vehicle = vehicle;
        this.RentalDays = rentDay;
        this.modeOfPayment = payVia;
    }
    public String getCustomerName() {return CustomerName;}
    public Vehicle getVehicle() {return vehicle;}
    public int getRentalDays() {return RentalDays;}
    public String getModeOfPayment() {return modeOfPayment;}

    public double calculateTotalCost() {
        return vehicle.calculateRentalCost(RentalDays);
    }



}
