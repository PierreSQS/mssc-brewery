package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<CustomerDto> handlePost(@RequestBody CustomerDto customerDto) {
        CustomerDto newCustomerDto = customerServ.saveNewCustomer(customerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/"+newCustomerDto.getId().toString());
        return  new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @PutMapping("{customerID}")
    public ResponseEntity<CustomerDto> handleUpdate(@PathVariable UUID customerID, CustomerDto customerDto) {
        customerServ.updateCustomer(customerID,customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("{customerID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerByID(@PathVariable UUID customerID) {
        customerServ.deleteCustomerByID(customerID);
    }
}

