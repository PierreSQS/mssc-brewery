package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerByID(UUID customerID);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerID, CustomerDto customerDto);

    void deleteCustomerByID(UUID customerID);
}
