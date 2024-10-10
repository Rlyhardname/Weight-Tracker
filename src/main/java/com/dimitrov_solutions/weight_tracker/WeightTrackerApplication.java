package com.dimitrov_solutions.weight_tracker;

import com.dimitrov_solutions.weight_tracker.enums.CountryCodeCapital;
import com.dimitrov_solutions.weight_tracker.enums.CountryCountryCode;
import com.dimitrov_solutions.weight_tracker.models.weather.utils.UrlFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeightTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeightTrackerApplication.class, args);
        String url = UrlFacade.createUrl(CountryCountryCode.Cuba.getCode(), CountryCodeCapital.CU.getCapitalCity());
        System.out.println(url);
//        WeatherHttpClientFacade facade = new WeatherHttpClientFacade(url);
//        WeatherMapFacade mapper = new WeatherMapFacade(new WeatherDetailsDtoMapper());
//        String body = facade.send();
//        mapper.mapTo(body, url);

    }

}
