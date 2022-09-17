package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    BeerDto beerDtoMock;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerServMock;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() {
        beerDtoMock = BeerDto.builder().id(UUID.randomUUID())
                .beerName("33 Export")
                .beerStyle("Blonde")
                .build();
    }

    @Test
    public void getBeer() throws Exception {
        // Given
        given(beerServMock.getBeerById(any())).willReturn(beerDtoMock);

        // When , Then
        mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.beerName").value(equalTo("33 Export")))
                .andExpect(jsonPath("$.beerStyle", is(equalTo("Blonde"))))
                .andDo(print());
    }

    @Test
    public void handlePost() throws Exception {
        // Given
        BeerDto beerDtoToSave = beerDtoMock;
        beerDtoToSave.setId(null);
        BeerDto savedBeerDto =  BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("New Beer")
                .beerStyle("Blonde")
                .build();
        given(beerServMock.saveNewBeer(any())).willReturn(savedBeerDto);

        // When, Then
        mockMvc.perform(post("/api/v1/beer")
                        .content(objectMapper.writeValueAsString(beerDtoToSave))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void handleUpdate() throws Exception {
        // Given
        BeerDto beerDtoUpdate = beerDtoMock;
        BeerDto updatedBeerDto =  BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Updated Beer")
                .beerStyle("Pils")
                .upc(123456789L)
                .build();
        given(beerServMock.saveNewBeer(any())).willReturn(updatedBeerDto);

        // When, Then
        mockMvc.perform(put("/api/v1/beer/{beerID}", beerDtoMock.getId())
                        .content(objectMapper.writeValueAsString(beerDtoUpdate))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}