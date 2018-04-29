package kpardyka.api.model;

import java.util.List;

public class ExchangeRates {
    private char table;
    private String currency;
    private String code;
    private List<Rate> rates;

    public char getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
