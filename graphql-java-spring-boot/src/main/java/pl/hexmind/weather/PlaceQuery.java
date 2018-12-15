package pl.hexmind.weather;

import lombok.extern.slf4j.Slf4j;
import pl.hexmind.weather.client.Location;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.hexmind.weather.client.MetaWeatherClient;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Slf4j
@Component
public class PlaceQuery implements GraphQLQueryResolver {

    private final MetaWeatherClient metaWeatherClient;

    public PlaceQuery(MetaWeatherClient metaWeatherClient) {
        this.metaWeatherClient = metaWeatherClient;
    }

    public List<Place> searchPlace(String query) {
        return metaWeatherClient.searchLocation(query).stream()
                .filter(l -> l.getLattLong() != null && l.getLattLong().contains(","))
                .map(this::toPlace)
                .collect(Collectors.toList());
    }

    private Place toPlace(Location location) {
        log.debug("{}", location);
        List<BigDecimal> lattLong = Arrays.stream(location.getLattLong().split(","))
                .map(BigDecimal::new)
                .collect(Collectors.toList());
        return Place.builder()
                .id(String.valueOf(location.getWoeid()))
                .title(location.getTitle())
                .type(location.getLocationType())
                .latitude(lattLong.get(0))
                .longitude(lattLong.get(1))
                .build();
    }
}
