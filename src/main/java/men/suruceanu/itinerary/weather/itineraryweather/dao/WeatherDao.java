package men.suruceanu.itinerary.weather.itineraryweather.dao;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class WeatherDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "clouds")
    private long clouds;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "added")
    private LocalDateTime added;

    public WeatherDao(String cityName, String countryCode, double temperature, long clouds, LocalDateTime timestamp) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.temperature = temperature;
        this.clouds = clouds;
        this.timestamp = timestamp;
        this.added = LocalDateTime.now();
    }
}
