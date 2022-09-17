package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerByID(UUID customerID) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Pierrot Mongonnam")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Sarah Mfam")
                .build();
    }

    @Override
    public void updateCustomer(UUID customerID, CustomerDto customerDto) {
        // TODO add implementation
    }

    @Override
    public void deleteCustomerByID(UUID customerID) {
        // TODO add implementation
    }
}
