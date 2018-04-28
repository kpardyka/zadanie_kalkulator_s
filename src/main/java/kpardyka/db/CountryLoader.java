package kpardyka.db;

import kpardyka.model.Country;
import kpardyka.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CountryLoader implements ApplicationRunner {

    private CountryRepository countryRepository;

    @Autowired
    public CountryLoader(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void run(ApplicationArguments args) {
        countryRepository.save(new Country("Poland", 19, 1200));
        countryRepository.save(new Country("The United Kingdom", "gbp", 25, 600));
        countryRepository.save(new Country("Germany", "eur", 20, 800));
    }
}