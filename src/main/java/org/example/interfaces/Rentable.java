package org.example.interfaces;

import org.example.user.Customer;

public interface Rentable {
    void rent(Customer customer, int days);
    void returnVehicle();
}
