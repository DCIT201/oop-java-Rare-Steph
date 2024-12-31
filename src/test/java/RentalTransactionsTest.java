import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.vehicles.Car;
import org.example.user.Customer;
import org.example.agency.RentalTransaction;

class RentalTransactionsTest {
    private RentalTransaction rentalTransaction;
    private Customer customer;
    private Car car;

    @BeforeEach
    void setUp() {
        // Creating a customer and vehicle to be used in the rental transaction
        customer = new Customer("CU001", "John Doe", "Abokobi");
        car = new Car("Toyota Camry", 50.0, "TC-5656", true);

        // Create a rental transaction for 3 days
        rentalTransaction = new RentalTransaction("John Doe", car, 3, "Credit Card");
    }

    @Test
    void testCalculateTotalCost() {
        // The expected rental cost for 3 days (50 base rate + 15 extra charge)
        double expectedCost = 95.0;

        // Test the total rental cost
        assertEquals(expectedCost, rentalTransaction.calculateTotalCost(), "The rental cost should be correct.");
    }

    @Test
    void testGetCustomer() {
        // Verify that the customer information is correctly retrieved
        assertEquals(customer, rentalTransaction.getCustomerName(), "The customer should be correctly retrieved.");
    }

    @Test
    void testGetVehicle() {
        // Verify that the vehicle information is correctly retrieved
        assertEquals(car, rentalTransaction.getVehicle(), "The vehicle should be correctly retrieved.");
    }

    @Test
    void testGetRentalDays() {
        // Verify that the rental days are correctly retrieved
        assertEquals(3, rentalTransaction.getRentalDays(), "The rental days should be correct.");
    }
}
