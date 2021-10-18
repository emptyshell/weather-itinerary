package men.suruceanu.itinerary.weather.itineraryweather.controllers;

import men.suruceanu.itinerary.weather.itineraryweather.dao.WeatherDao;
import men.suruceanu.itinerary.weather.itineraryweather.services.WeatherAPI;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/weather")
public class WeatherController {

    private final WeatherAPI weatherAPI;

    public WeatherController(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    @GetMapping("/fetch")
    public List<WeatherDao> getWeather(
            @RequestParam(name = "city") String city,
            @RequestParam(value = "units", required = false, defaultValue = "metric") String units,
            @RequestParam(value = "lang", required = false) String lang
    ) {
        return weatherAPI.fetch(city, units, lang);
    }

}
