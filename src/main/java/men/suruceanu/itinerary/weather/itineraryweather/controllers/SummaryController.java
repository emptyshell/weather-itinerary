package men.suruceanu.itinerary.weather.itineraryweather.controllers;

import men.suruceanu.itinerary.weather.itineraryweather.dao.ItineraryDao;
import men.suruceanu.itinerary.weather.itineraryweather.dao.WeatherDao;
import men.suruceanu.itinerary.weather.itineraryweather.repositories.ItineraryRepo;
import men.suruceanu.itinerary.weather.itineraryweather.repositories.WeatherRepo;
import men.suruceanu.itinerary.weather.itineraryweather.services.WeatherAPI;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/summary")
public class SummaryController {

    private final ItineraryRepo itineraryRepo;
    private final WeatherRepo weatherRepo;

    public SummaryController(ItineraryRepo itineraryRepo, WeatherRepo weatherRepo) {
        this.itineraryRepo = itineraryRepo;
        this.weatherRepo = weatherRepo;
    }

    @GetMapping("/{itineraryName}")
    public String getSummary(@PathVariable String itineraryName) {

        double cloudsAvg = 0.0;
        double tempAvg = 0.0;

        List<ItineraryDao> itineraryDaoList = itineraryRepo.findAllByName(itineraryName);

        for (ItineraryDao itineraryDao : itineraryDaoList) {
            List<WeatherDao> weatherDaoList = weatherRepo.findAllByCityName(itineraryDao.getCity());
            cloudsAvg = weatherDaoList.stream().mapToLong(WeatherDao::getClouds).average().orElse(Double.NaN);
            tempAvg = weatherDaoList.stream().mapToDouble(WeatherDao::getTemperature).average().orElse(Double.NaN);
        }

        StringBuilder stringBuilder = new StringBuilder();

        if(cloudsAvg > 15) {
            stringBuilder.append("Please take an umbrella. ");
        }

        if(tempAvg < 5) {
            stringBuilder.append("Please take a coat. ");
        }

        return stringBuilder.toString();
    }

}
