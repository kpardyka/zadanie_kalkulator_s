package kpardyka.controller;

import kpardyka.exchangeRates.NBPApi;
import kpardyka.model.Country;
import kpardyka.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class SalaryController {

    @Autowired
    private NBPApi NBPApi;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/")
    public Iterable<Country> getAll() {
        return countryRepository.findAll();
    }

    @RequestMapping(value = "/country/{id}/{dailySalary}", method = RequestMethod.GET)
    public ResponseEntity getCountry(@PathVariable Long id, @PathVariable Double dailySalary) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            String code = country.get().getCode();
            return ResponseEntity.ok(NBPApi.getExchangeRate(code));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
