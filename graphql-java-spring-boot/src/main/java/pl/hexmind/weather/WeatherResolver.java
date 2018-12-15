package pl.hexmind.weather;

import pl.hexmind.weather.client.Weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class WeatherResolver implements GraphQLResolver<Weather> {

    private final String metaweatherUrl;

    public WeatherResolver(@Value("${metaweather.url}") String metaweatherUrl) {
        this.metaweatherUrl = metaweatherUrl;
    }

    public String weatherStateIcon(Weather weather) {
        return metaweatherUrl
                + "static/img/weather/png/64/"
                + weather.getWeatherStateAbbr()
                + ".png";
    }
}
