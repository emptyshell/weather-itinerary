package men.suruceanu.itinerary.weather.itineraryweather.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "itinerary")
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ItineraryDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    public ItineraryDao(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
