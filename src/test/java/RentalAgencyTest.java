import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.vehicles.Car;
import org.example.vehicles.Motorcycle;
import org.example.vehicles.Truck;
import org.example.user.Customer;
import org.example.agency.RentalAgency;
import org.example.exceptions.VehicleNotAvailableException;
import org.example.exceptions.InvalidRentalDurationException;

class RentalAgencyTest {

    private RentalAgency agency;
    private Customer customer;
    private Car car;
    private Motorcycle motorcycle;
    private Truck truck;


    @BeforeEach
    void setUp() {
        agency = new RentalAgency("City Rental Agency", "No. 1 Fanofaa Street, Accra");
        customer = new Customer("Nana Kwabena Adoma", "C002", "Tepa");
        car = new Car("Tesla Cybertruck", 45.0, "KR-4040", true);
        motorcycle = new Motorcycle("Dugatti", 30.0, "M005", false);
        truck = new Truck("Mitsibushi L200", "T007", 75.0, false);

        // Add vehicles to the agency
        agency.addVehicle(car);
        agency.addVehicle(motorcycle);
    }

    @Test
    void testRentVehicleSuccessfully() {
        try {
            agency.rentVehicle(customer, car, 3, "Credit Card");  // Rent car for 3 days
            assertFalse(car.isAvailableForRental());  // Car should no longer be available
        } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testRentVehicleNotAvailable() {
        try {
            agency.rentVehicle(customer, car, 3, "Cash");  // Rent car for 3 days
            agency.rentVehicle(customer, car, 2, "Cash");  // Try renting it again
            fail("Expected VehicleNotAvailableException");
        } catch (VehicleNotAvailableException e) {
            assertEquals("Vehicle is not available for rental.", e.getMessage());
        } catch (InvalidRentalDurationException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testRentVehicleWithInvalidDuration() {
        try {
            agency.rentVehicle(customer, motorcycle, 7, "Mobile Money");  // Invalid rental duration (0 days)
            fail("Expected InvalidRentalDurationException");
        } catch (InvalidRentalDurationException e) {
            assertEquals("Rental days must be greater than zero.", e.getMessage());
        } catch (VehicleNotAvailableException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testReturnVehicleSuccessfully() {
        try {
            agency.rentVehicle(customer, car, 3, "Credit Card");  // Rent car for 3 days
            agency.returnVehicle(customer, car);   // Return the car
            assertTrue(car.isAvailableForRental());  // Car should now be available again
        } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testReturnVehicleNotRented() {
        try {
            agency.returnVehicle(customer, car);  // Try to return a car that wasn't rented
            fail("Expected VehicleNotAvailableException");
        } catch (VehicleNotAvailableException e) {
            assertEquals("Vehicle not found in fleet.", e.getMessage());
        }
    }

    @Test
    void testGenerateCustomerReport() {
        try {
            agency.rentVehicle(customer, truck, 3, "Cash");  // Rent car for 3 days
            agency.generateCustomerReport(customer);  // Generate report
        } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testGenerateTotalIncomeReport() {
        try {
            agency.rentVehicle(customer, truck, 3, "Credit Card");  // Rent car for 3 days
            agency.rentVehicle(customer, motorcycle, 2, "Cash");  // Rent motorcycle for 2 days
            agency.generateTotalIncomeReport();  // Generate total income report
        } catch (VehicleNotAvailableException | InvalidRentalDurationException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
}
