package kpardyka.exchangeRates;

import java.util.List;

class ExchangeRates {
    private char table;
    private String currency;
    private String code;
    private List<Rate> rates;

    char getTable() {
        return table;
    }

    String getCurrency() {
        return currency;
    }

    String getCode() {
        return code;
    }

   List<Rate> getRates() {
        return rates;
    }

    void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
