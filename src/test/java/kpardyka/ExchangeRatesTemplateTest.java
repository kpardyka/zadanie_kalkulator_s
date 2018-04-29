package kpardyka;

import kpardyka.api.model.ExchangeRates;
import kpardyka.api.model.Rate;
import kpardyka.api.operation.ExchangeRatesTemplate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static java.lang.Double.valueOf;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ExchangeRatesTemplateTest {

    private ExchangeRatesTemplate template;
    private MockRestServiceServer mockServer;

    @Before
    public void setup() {
        RestTemplate restTemplate = new RestTemplate();

        this.template = new ExchangeRatesTemplate(restTemplate);
        ReflectionTestUtils.setField(template, "exchangeRateApiPath", "http://api.nbp.pl/api/exchangerates/rates/a/{code}/");
        this.mockServer = MockRestServiceServer.createServer(template.getRestTemplate());
    }

    @Test
    public void shouldCreateCorrectExchangeRates() {
        mockServer.expect(requestTo("http://api.nbp.pl/api/exchangerates/rates/a/eur/"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ExchangeRatesAPI"), MediaType.APPLICATION_JSON));

        ExchangeRates exchangeRates = template.getExchangeRateFromAPI("eur");
        Rate rate = exchangeRates.getRates().get(0);

        assertEquals('A', exchangeRates.getTable());
        assertEquals("euro", exchangeRates.getCurrency());
        assertEquals("EUR", exchangeRates.getCode());

        assertEquals("083/A/NBP/2018", rate.getNo());
        assertEquals("2018-04-27", rate.getEffectiveDate());
        assertEquals(valueOf(4.2259), rate.getMid());
    }


    private Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json");
    }
}
