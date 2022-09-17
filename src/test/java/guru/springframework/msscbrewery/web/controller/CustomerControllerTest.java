package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    private CustomerDto customerDtoMock;

    @MockBean
    CustomerService customerServMock;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() {
        customerDtoMock = CustomerDto.builder()
                .id(UUID.fromString("9e425689-d043-4528-9487-ae7a7e6f77fb"))
                .name("Pierrot Mongonnam")
                .build();
    }

    @Test
    public void getByID() throws Exception {
        // Given
        given(customerServMock.getCustomerByID(any())).willReturn(customerDtoMock);
        mockMvc.perform(get("/api/v1/customer/{customerID}", UUID.fromString("9e425689-d043-4528-9487-ae7a7e6f77fb")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(equalTo("Pierrot Mongonnam"))))
                .andDo(print());
    }
}