package kpardyka.exchangeRates;

import java.util.List;

class ExchangeRates {

    private String code;
    private List<Rate> rates;

    String getCode() {
        return code;
    }

    List<Rate> getRates() {
        return rates;
    }
}
