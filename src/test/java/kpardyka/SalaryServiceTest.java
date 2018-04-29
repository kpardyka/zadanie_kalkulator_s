package kpardyka;

import kpardyka.model.Country;
import kpardyka.service.SalaryService;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static org.junit.Assert.assertTrue;


public class SalaryServiceTest {
    private SalaryService salaryService = new SalaryService();


    @Test
    public void shouldProperlyCalculateNetSalaryFromGrossDailySalaryInPoland() {
        Country country = new Country("Poland", 19, 1200);
        BigDecimal exchangeRate = ONE;
        BigDecimal grossSalary = valueOf(100);
        BigDecimal expectedResult = valueOf(840.34);
        BigDecimal actualResult = salaryService.calculateNetSalaryInPLN(country, exchangeRate, grossSalary);
        assertTrue(expectedResult.compareTo(actualResult) == 0);
    }

    @Test
    public void shouldProperlyCalculateNetSalaryFromGrossDailySalaryInOtherCountriesThanPoland() {
        Country country = new Country("Test country", 20, 1000);
        BigDecimal exchangeRate = valueOf(4);
        BigDecimal grossSalary = valueOf(100);
        BigDecimal expectedResult = valueOf(4000);
        BigDecimal actualResult = salaryService.calculateNetSalaryInPLN(country, exchangeRate, grossSalary);
        assertTrue(expectedResult.compareTo(actualResult) == 0);

    }

}

