package men.suruceanu.itinerary.weather.itineraryweather.task;

import men.suruceanu.itinerary.weather.itineraryweather.repositories.WeatherRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanerTask {

    private final WeatherRepo weatherRepo;

    public CleanerTask(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    @Scheduled(fixedRate = 3600000)
    public void cleanCache() {
        weatherRepo.deleteAll();
    }

}
