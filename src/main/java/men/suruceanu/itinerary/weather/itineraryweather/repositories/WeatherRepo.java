package men.suruceanu.itinerary.weather.itineraryweather.repositories;

import men.suruceanu.itinerary.weather.itineraryweather.dao.WeatherDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepo extends JpaRepository<WeatherDao, Long> {

    Optional<WeatherDao> findByCityName(String cityName);

}
