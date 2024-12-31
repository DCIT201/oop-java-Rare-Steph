package org.example.main;

import org.example.agency.RentalAgency;
import org.example.exceptions.CustomerNotEligibleException;
import org.example.exceptions.InvalidRentalDurationException;
import org.example.exceptions.VehicleNotAvailableException;
import org.example.user.Customer;
import org.example.vehicles.Car;
import org.example.vehicles.Motorcycle;
import org.example.vehicles.Truck;

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Car car1 = new Car("Lamborghini", 20.0, "C-001", true);
        Motorcycle motorcycle1 = new Motorcycle("Hamshu", 30.0, "MC-001", true);
        Truck truck1 = new Truck("Mitsubishi L200", "TR-001", 50, true);

        //Create a rental Agency
        RentalAgency agency = new RentalAgency("Cooperate Rentals", "NorthVille");

        //Add vehicles to the agency
        agency.addVehicle(car1);
        agency.addVehicle(motorcycle1);
        agency.addVehicle(truck1);

        //Create a customer
        Customer customer1 = new Customer("Kofi Ama", "KA-CA001", "Abyss");

        //Simulate customer renting a vehicle
        try {
            if (customer1.rentalEligibility()) {
                try {
                    agency.rentVehicle(customer1, car1, 2, "Cash");
                    customer1.addRental(car1.getVehicleId());
                } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
                    System.out.println("Error renting a Vehicle: " + e.getMessage());
                }
            }
            else {
                throw new CustomerNotEligibleException("Customer is not eligible for rental.");
            }
        } catch (CustomerNotEligibleException e) {
            System.out.println(e.getMessage());
        }

        //Returning the Vehicle
        try {
            agency.returnVehicle(customer1,car1);
    } catch (VehicleNotAvailableException e) {
            System.out.println(" Error returning Vehicle: " + e.getMessage());
        }

        //Generate Customer report
        agency.generateCustomerReport(customer1);

        //Generate total income report
        agency.generateTotalIncomeReport();


}


}
