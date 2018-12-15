package pl.hexmind.weather.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "metaweather", url = "${metaweather.url}")
public interface MetaWeatherClient {

    @GetMapping(value = "api/location/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Location> searchLocation(@RequestParam("query") String query);

    @GetMapping(value = "api/location/{woeid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Forecast getForecast(@PathVariable("woeid") int locationId);
}
