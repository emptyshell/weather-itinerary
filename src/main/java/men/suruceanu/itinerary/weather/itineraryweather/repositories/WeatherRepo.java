package men.suruceanu.itinerary.weather.itineraryweather.repositories;

import men.suruceanu.itinerary.weather.itineraryweather.dao.WeatherDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepo extends JpaRepository<WeatherDao, Long> {

    List<WeatherDao> findAllByCityName(String cityName);
    Boolean existsByCityName(String cityName);
    @Query("DELETE FROM WeatherDao weatherDao WHERE TIMESTAMPDIFF(HOUR, weatherDao.added, CURRENT_TIMESTAMP) >= 1")
    void deleteAll();

}
