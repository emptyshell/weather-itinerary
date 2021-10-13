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
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL).queryParam("appid", API_KEY)
                .queryParamIfPresent("q", Optional.ofNullable(cityName))
                .queryParamIfPresent("units", Optional.ofNullable(units))
                .queryParamIfPresent("lang", Optional.ofNullable(lang));

        WeatherResponse weatherResponse = restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(httpHeaders), WeatherResponse.class)
                .getBody();

        List<WeatherDao> weatherDaoList = new ArrayList<>();

        Objects.requireNonNull(weatherResponse).getList().forEach(weatherData -> {
            WeatherDao weatherDao = new WeatherDao(
                    weatherResponse.getCity().getName(),
                    weatherResponse.getCod(),
                    weatherData.getMain().getTemp(),
                    weatherData.getClouds().getAll());

            weatherDaoList.add(weatherDao);

            weatherRepo.save(weatherDao);
        });

        return weatherDaoList;
    }
}
