package kpardyka.api.operation;

import kpardyka.api.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class ExchangeRatesTemplate implements ExchangeRatesOperations {

    private final RestTemplate restTemplate;

    @Value("${api.nbp.exchange-rate-path}")
    private String exchangeRateApiPath;

    public ExchangeRatesTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ExchangeRates getExchangeRateFromAPI(String code) {
        return restTemplate.getForObject(exchangeRateApiPath, ExchangeRates.class, code);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
