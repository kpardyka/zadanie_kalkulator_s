package kpardyka.controller;

import kpardyka.error.ErrorData;
import kpardyka.model.Country;
import kpardyka.repository.CountryRepository;
import kpardyka.service.ExchangeRatesService;
import kpardyka.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;


@RestController
public class SalaryController {

    private ExchangeRatesService exchangeRatesService;
    private SalaryService salaryService;
    private CountryRepository countryRepository;

    public SalaryController(CountryRepository countryRepository, SalaryService salaryService, ExchangeRatesService exchangeRatesService){
        this.countryRepository = countryRepository;
        this.salaryService = salaryService;
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/countries")
    public Iterable<Country> getAll() {
        return countryRepository.findAll();
    }

    @GetMapping(value = "/country/{id}/{dailySalary}")
    public ResponseEntity calculateSalary(@PathVariable Long id, @PathVariable Double dailySalary) {
        Optional<Country> country = countryRepository.findById(id);
        String url = "http://localhost:8080/country/" + id +"/" + dailySalary;

        if (dailySalary < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorData(url, "Daily Salary must be a nonnegative value"));
        }
        if (country.isPresent()) {
            String code = country.get().getCode();
            BigDecimal exchangeRate = valueOf(exchangeRatesService.getExchangeRate(code));
            BigDecimal netMonthSalary = salaryService.calculateNetSalaryInPLN(country.get(), exchangeRate, valueOf(dailySalary));
            return ResponseEntity.ok(netMonthSalary);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorData(url,"Country with id = " + id + " not found"));
        }
    }

}
