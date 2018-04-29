package kpardyka.service;

import kpardyka.api.model.Rate;
import kpardyka.api.operation.ExchangeRatesTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRatesService {

    private ExchangeRatesTemplate template;

    public ExchangeRatesService(ExchangeRatesTemplate template) {
        this.template = template;
    }

    private static final double POLISH_RATE = 1.0;

    public double getExchangeRate(String code) {
        if (code == null) {
            return POLISH_RATE;
        }

        List<Rate> rates = template.getExchangeRateFromAPI(code).getRates();

        Rate rate = rates.get(0);
        return rate.getMid();
    }
}
