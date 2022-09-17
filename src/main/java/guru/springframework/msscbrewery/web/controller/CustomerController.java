package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerServ;

    public CustomerController(CustomerService customerServ) {
        this.customerServ = customerServ;
    }

    @GetMapping("/{customerID}")
    public CustomerDto getByID(@PathVariable UUID customerID) {
        return customerServ.getCustomerByID(customerID);
    }
}
