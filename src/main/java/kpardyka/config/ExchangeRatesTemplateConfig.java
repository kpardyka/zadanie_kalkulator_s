package kpardyka.config;

import kpardyka.api.operation.ExchangeRatesTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ExchangeRatesTemplateConfig {

    @Value("${exchange-rate.timout}")
    private int timeout;

    @Bean
    ExchangeRatesTemplate exchangeRatesTemplate() {
        return new ExchangeRatesTemplate(restTemplate());
    }

    private RestTemplate restTemplate() {
        return new RestTemplate(getClientHttpRequestFactory());
    }


    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
