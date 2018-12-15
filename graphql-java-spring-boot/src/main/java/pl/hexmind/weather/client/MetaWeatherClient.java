package pl.hexmind.weather.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "metaweather", url = "${metaweather.url:https://www.metaweather.com/api/}")
public interface MetaWeatherClient {

    @GetMapping(value = "/location/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Location> searchLocation(@RequestParam("query") String query);

    @GetMapping(value = "/weather/{woeid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Forecast getForecast(@PathVariable("woeid") int woeid);
}
