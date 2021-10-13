package men.suruceanu.itinerary.weather.itineraryweather.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class WeatherData {

    private long dt;
    private WeatherMain main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private WeatherSys sys;
    private String dt_txt;

}
