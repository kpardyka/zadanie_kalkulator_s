package kpardyka.controller;

import kpardyka.model.Country;
import kpardyka.repository.CountryRepository;
import kpardyka.service.ExchangeRatesService;
import kpardyka.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;


@RestController
public class SalaryController {

    @Autowired
    private ExchangeRatesService service;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/")
    public Iterable<Country> getAll() {
        return countryRepository.findAll();
    }

    @RequestMapping(value = "/country/{id}/{dailySalary}", method = RequestMethod.GET)
    public ResponseEntity calculateSalary(@PathVariable Long id, @PathVariable Double dailySalary) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            String code = country.get().getCode();
            BigDecimal exchangeRate = valueOf(service.getExchangeRate(code));
            BigDecimal netMonthSalary = salaryService.calculateNetSalaryInPLN(country.get(), exchangeRate, valueOf(dailySalary));
            return ResponseEntity.ok(netMonthSalary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
