package pl.hexmind.weather.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Forecast {

    private String title;
    private List<Weather> consolidatedWeather;

}
