package kpardyka;

import kpardyka.api.operation.ExchangeRatesTemplate;
import kpardyka.controller.SalaryController;
import kpardyka.model.Country;
import kpardyka.repository.CountryRepository;
import kpardyka.service.ExchangeRatesService;
import kpardyka.service.SalaryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


public class SalaryControllerTest {
    private SalaryController salaryController;
    private ExchangeRatesTemplate template;
    private MockRestServiceServer mockServer;
    private RestTemplate restTemplate;
    private CountryRepository countryRepository;


    @Before
    public void setup() {
        restTemplate = new RestTemplate();

        countryRepository = Mockito.mock(CountryRepository.class);
        SalaryService salaryService = Mockito.mock(SalaryService.class);
        ExchangeRatesService exchangeRatesService = Mockito.mock(ExchangeRatesService.class);
        Mockito.when(countryRepository.findById(1L)).thenReturn(Optional.of(new Country("Test country", 19, 1000)));
        salaryController = new SalaryController(countryRepository, salaryService, exchangeRatesService);

        this.template = new ExchangeRatesTemplate(restTemplate);
        ReflectionTestUtils.setField(template, "exchangeRateApiPath", "http://api.nbp.pl/api/exchangerates/rates/a/{code}/");
        this.mockServer = MockRestServiceServer.createServer(template.getRestTemplate());
    }

    @Test
    public void repositoryShouldReturnAllCountries() {
        Country country1 = new Country("Test country", 30, 2000);
        Country country2 = new Country("Another test country", "test", 20, 1000);
        List<Country> countries = Arrays.asList(country1, country2);
        Mockito.when(countryRepository.findAll()).thenReturn(countries);
        assertTrue(salaryController.getAll().spliterator().getExactSizeIfKnown() == 2);
    }


    @Test
    public void controllerShouldReturnBadRequestWhenSalaryIsLessThanZero() {
        assertEquals(400, salaryController.calculateSalary(1L, -1.0).getStatusCodeValue());
    }

    @Test
    public void controllerShouldReturnNotFoundWhenGivenIdIsNotInDatabase() {
        assertEquals(404, salaryController.calculateSalary(3L, 100.0).getStatusCodeValue());
    }

    @Test
    public void shouldReturnStatusOkWhenGivenIdAndDailySalaryIsProperty() {
        mockServer.expect(requestTo("http://api.nbp.pl/api/exchangerates/rates/a/eur/"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("ExchangeRatesAPI"), MediaType.APPLICATION_JSON));
        assertEquals(200, salaryController.calculateSalary(1L, 100.0).getStatusCodeValue());
    }

    private Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json");
    }
}
