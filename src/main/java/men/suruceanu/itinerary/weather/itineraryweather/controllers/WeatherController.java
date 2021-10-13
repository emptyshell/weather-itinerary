package men.suruceanu.itinerary.weather.itineraryweather.controllers;

import men.suruceanu.itinerary.weather.itineraryweather.dao.WeatherDao;
import men.suruceanu.itinerary.weather.itineraryweather.services.WeatherAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherAPI weatherAPI;

    public WeatherController(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    @GetMapping("/weather")
    public List<WeatherDao> getWeather(
            @RequestParam(name = "city") String city,
            @RequestParam(value = "units", required = false) String units,
            @RequestParam(value = "lang", required = false) String lang
    ) {
        return weatherAPI.fetch(city, units, lang);
    }

}
