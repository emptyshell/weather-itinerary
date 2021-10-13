package men.suruceanu.itinerary.weather.itineraryweather.services;

import men.suruceanu.itinerary.weather.itineraryweather.dao.WeatherDao;

import java.util.List;

public interface WeatherAPI {

    List<WeatherDao> fetch(String cityName, String units, String lang);

}
