package men.suruceanu.itinerary.weather.itineraryweather.repositories;

import men.suruceanu.itinerary.weather.itineraryweather.dao.ItineraryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepo extends JpaRepository<ItineraryDao, String> {

    List<ItineraryDao> findByName(String name);
    List<ItineraryDao> findAllByName(String name);

}
