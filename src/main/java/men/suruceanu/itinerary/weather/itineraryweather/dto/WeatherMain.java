package men.suruceanu.itinerary.weather.itineraryweather.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class WeatherMain {

    private double temp;
    private long pressure;
    private long humidity;
    private double sea_level;
    private double temp_min;
    private double temp_max;
    private double temp_kf;

}
