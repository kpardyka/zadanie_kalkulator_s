package kpardyka.api.operation;

import kpardyka.api.model.ExchangeRates;

public interface ExchangeRatesOperations {
    ExchangeRates getExchangeRateFromAPI(String code);
}
