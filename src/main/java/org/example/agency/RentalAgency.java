package org.example.agency;

import org.example.exceptions.InvalidRentalDurationException;
import org.example.exceptions.VehicleNotAvailableException;
import org.example.interfaces.Rentable;
import org.example.user.Customer;
import org.example.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class RentalAgency {
    private String agencyName;
    private String agencyAddress;
    private List<Vehicle> fleet;
    private List<RentalTransaction> transactions;

    public RentalAgency(String agencyName, String agencyAddress) {
        this.agencyName = agencyName;
        this.agencyAddress = agencyAddress;
        this.fleet = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }


    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public void addVehicle(Vehicle vehicle) {
        if (!(vehicle instanceof Rentable)) {
            throw new IllegalArgumentException("Vehicle is not a rentable");
        }
        fleet.add(vehicle);
    }

    public void rentVehicle(Customer customer, Vehicle vehicle, int days, String payVia) throws
            VehicleNotAvailableException, InvalidRentalDurationException {
        if (!vehicle.isAvailableForRental()) {
            throw new VehicleNotAvailableException("Vehicle is not available for rental");
        }
        if (days <= 0) {
            throw new InvalidRentalDurationException("Days must be greater than 0");
        }
        if (vehicle instanceof Rentable) {
            ((Rentable) vehicle).rent(customer, days);
        }

        RentalTransaction transaction = new RentalTransaction(customer.getCustomerName(),vehicle,days, payVia);
        transactions.add(transaction);
        System.out.println("Vehicle rented successfully.");
    }

    public void returnVehicle(Customer customer, Vehicle vehicle) throws
            VehicleNotAvailableException {
        if (!fleet.contains(vehicle)) {
            throw new VehicleNotAvailableException("Vehicle is not available for rental");
        }
        if (vehicle instanceof Rentable) {
            ((Rentable) vehicle).returnVehicle();
            System.out.println("Vehicle returned successfully.");
        }
    }

    public void generateCustomerReport(Customer customer) {
        System.out.println("Rental history for customer: "+ customer.getCustomerName());
        List<String> rentals = customer.getRentalHistory();
        if (rentals.isEmpty()) {
            System.out.println("This customer has no rental history");}
        else{
            for (String vehicleID : rentals) {
                System.out.println("Rented Vehicle: "+ vehicleID);
            }
        }
    }

    public void generateTotalIncomeReport() {
            double totalIncome = 0;
            for (RentalTransaction transaction : transactions) {
                totalIncome += transaction.calculateTotalCost();
            }
            System.out.println("Total income: "+ totalIncome+ " dollars");
    }
}










