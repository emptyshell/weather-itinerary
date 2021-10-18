package men.suruceanu.itinerary.weather.itineraryweather.services;

import men.suruceanu.itinerary.weather.itineraryweather.dao.WeatherDao;
import men.suruceanu.itinerary.weather.itineraryweather.dto.WeatherResponse;
import men.suruceanu.itinerary.weather.itineraryweather.repositories.WeatherRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class WeatherAPIImpl implements WeatherAPI {

    private final RestTemplate restTemplate;
    private final WeatherRepo weatherRepo;
    @Value("${weather.api.key}")
    private String API_KEY;
    @Value("${weather.api.url}")
    private String API_URL;

    public WeatherAPIImpl(RestTemplate restTemplate, WeatherRepo weatherRepo) {
        this.restTemplate = restTemplate;
        this.weatherRepo = weatherRepo;
    }

    @Override
    public List<WeatherDao> fetch(String cityName, String units, String lang) {

        if (weatherRepo.existsByCityName(cityName)) {
            return weatherRepo.findAllByCityName(cityName);
        }

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL).queryParam("appid", API_KEY)
                .queryParamIfPresent("q", Optional.of(cityName.replaceAll(" ", "+")))
                .queryParamIfPresent("units", Optional.ofNullable(units))
                .queryParamIfPresent("lang", Optional.ofNullable(lang));

        WeatherResponse weatherResponse = restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(httpHeaders), WeatherResponse.class)
                .getBody();

        List<WeatherDao> weatherDaoList = new ArrayList<>();

        Objects.requireNonNull(weatherResponse).getList().forEach(weatherData -> {
            WeatherDao weatherDao = new WeatherDao(
                    cityName,
                    weatherResponse.getCod(),
                    weatherData.getMain().getTemp(),
                    weatherData.getClouds().getAll(),
                    LocalDateTime.ofInstant(Instant.ofEpochSecond(weatherData.getDt()), ZoneId.systemDefault())
            );

            weatherDaoList.add(weatherDao);
            if (weatherDao.getTimestamp().isAfter(
                    LocalDateTime.of(
                            weatherDao.getTimestamp().getYear(),
                            weatherDao.getTimestamp().getMonth(),
                            weatherDao.getTimestamp().getDayOfMonth(),
                            11,
                            59)
            ) && weatherDao.getTimestamp().isBefore(
                    LocalDateTime.of(
                            weatherDao.getTimestamp().getYear(),
                            weatherDao.getTimestamp().getMonth(),
                            weatherDao.getTimestamp().getDayOfMonth(),
                            18,
                            1)
            )) {
                weatherRepo.save(weatherDao);
            }
        });

        return weatherDaoList;
    }

    @Override
    public List<WeatherDao> get(String cityName) {
        return weatherRepo.findAllByCityName(cityName);
    }
}
