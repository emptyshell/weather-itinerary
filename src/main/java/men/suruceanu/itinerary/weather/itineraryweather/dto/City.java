package men.suruceanu.itinerary.weather.itineraryweather.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class City {

    private long id;
    private String name;
    private Coords coord;
    private String country;

}
