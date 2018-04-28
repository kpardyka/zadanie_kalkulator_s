package kpardyka.exchangeRates;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class NBPApi {

    @Value("${api.nbp.exchange-rate-path}")
    private String exchangeRateApiPath;

    private static final double POLISH_RATE = 1.0;

    public double getExchangeRate(String code) {
        if (code == null){
            return POLISH_RATE;
        }
        RestTemplate restTemplate = new RestTemplate();

        ExchangeRates exchangeRates = restTemplate.getForObject(exchangeRateApiPath, ExchangeRates.class, code);
        List<Rate> rates = exchangeRates.getRates();

        Rate rate = rates.get(0);
        return rate.getMid();
    }
}
