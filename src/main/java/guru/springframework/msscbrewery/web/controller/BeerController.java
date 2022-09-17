package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Modified by Pierrot on 2022-09-17.
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public BeerDto getBeer(@PathVariable("beerId") UUID beerId){

        return beerService.getBeerById(beerId);
    }

    @PostMapping
    public ResponseEntity<BeerDto> handlePost(@RequestBody BeerDto beerDto) {
        BeerDto savedBeerDto = beerService.saveNewBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/beer/"+savedBeerDto.getId().toString());
        return  new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{beerID}")
    public ResponseEntity<BeerDto> handleUpdate(@PathVariable UUID beerID, BeerDto beerDto) {
        beerService.updateBeer(beerID,beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
