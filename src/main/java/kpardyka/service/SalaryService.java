package kpardyka.service;

import kpardyka.model.Country;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;

@Component
public class SalaryService {

    private static final int WORK_DAYS_NUMBER = 22;

    public BigDecimal calculateNetSalaryInPLN(Country country, BigDecimal exchangeRate, BigDecimal dailySalary) {
        BigDecimal grossMonthSalary = calculateGrossMonthSalary(dailySalary);
        BigDecimal netMonthSalary = calculateNetMonthSalary(grossMonthSalary, country);
        BigDecimal netMonthSalaryInPLN = netMonthSalary.multiply(exchangeRate);
        return netMonthSalaryInPLN.setScale(2, ROUND_HALF_UP);
    }

    private BigDecimal calculateNetMonthSalary(BigDecimal grossMonthSalary, Country country) {
        int tax = country.getTax();
        BigDecimal costOfGettingIncome = valueOf(country.getCostOfGettingIncome());
        BigDecimal grossSalaryPercent = valueOf(100 + tax);
        BigDecimal grossSalaryWithoutCostOfGettingIncome = grossMonthSalary.subtract(costOfGettingIncome);
        BigDecimal netSalary = calculateGrossSalaryToNetSalary(grossSalaryPercent, grossSalaryWithoutCostOfGettingIncome);
        return netSalary;
    }

    private BigDecimal calculateGrossSalaryToNetSalary(BigDecimal grossSalaryPercent, BigDecimal grossSalary) {
        return grossSalary.multiply(valueOf( 100)).divide(grossSalaryPercent, ROUND_HALF_UP);
    }

    private BigDecimal calculateGrossMonthSalary(BigDecimal dailySalary) {
        return dailySalary.multiply(valueOf(WORK_DAYS_NUMBER));
    }
}
