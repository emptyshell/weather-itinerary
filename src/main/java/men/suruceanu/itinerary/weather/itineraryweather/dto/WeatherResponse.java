package men.suruceanu.itinerary.weather.itineraryweather.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class WeatherResponse {

    private String cod;
    private String message;
    private List<WeatherData> list;
    private City city;

}
