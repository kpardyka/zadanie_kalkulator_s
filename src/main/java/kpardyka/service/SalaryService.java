package kpardyka.service;

import kpardyka.model.Country;
import org.springframework.stereotype.Component;

@Component
public class SalaryService {

    private static final int WORK_DAYS_NUMBER = 22;

    public double calculateNeSalaryInPLN(Country country, double exchangeRate, double dailySalary) {
        double grossMonthSalary = calculateGrossMonthSalary(dailySalary);
        double netMonthSalary = calculateNetMonthSalary(grossMonthSalary, country);
        return netMonthSalary * exchangeRate;
    }

    private double calculateNetMonthSalary(double grossMonthSalary, Country country) {
        int tax = country.getTax();
        double costOfGettingIncome = country.getCostOfGettingIncome();
        int grossSalaryPercent = 100 + tax;
        double netSalary = (grossMonthSalary - costOfGettingIncome) * 100/ grossSalaryPercent;
        return Math.round(netSalary);
    }

    private double calculateGrossMonthSalary(double dailySalary) {
        return dailySalary * WORK_DAYS_NUMBER;
    }
}
