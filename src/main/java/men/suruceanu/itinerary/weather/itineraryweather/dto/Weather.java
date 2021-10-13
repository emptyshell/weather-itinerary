package men.suruceanu.itinerary.weather.itineraryweather.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
}
