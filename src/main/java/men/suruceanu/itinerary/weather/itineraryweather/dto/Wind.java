package men.suruceanu.itinerary.weather.itineraryweather.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Wind {

    private double speed;
    private int deg;
    private double gust;

}
