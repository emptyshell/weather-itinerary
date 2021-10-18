package men.suruceanu.itinerary.weather.itineraryweather.controllers;

import men.suruceanu.itinerary.weather.itineraryweather.dao.ItineraryDao;
import men.suruceanu.itinerary.weather.itineraryweather.dao.WeatherDao;
import men.suruceanu.itinerary.weather.itineraryweather.repositories.ItineraryRepo;
import men.suruceanu.itinerary.weather.itineraryweather.services.WeatherAPI;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(path = "/itinerary")
public class ItineraryController {

    private final ItineraryRepo itineraryRepo;

    public ItineraryController(ItineraryRepo itineraryRepo) {
        this.itineraryRepo = itineraryRepo;
    }

    @GetMapping("/{itineraryName}")
    public List<ItineraryDao> get(@PathVariable String itineraryName) {
        return itineraryRepo.findAllByName(itineraryName);
    }

    @PostMapping("/add")
    public ItineraryDao update(@RequestBody ItineraryDao itineraryDao) {
        return itineraryRepo.save(itineraryDao);
    }
}
