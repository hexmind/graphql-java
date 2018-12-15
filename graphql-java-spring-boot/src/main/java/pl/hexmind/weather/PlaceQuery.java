package pl.hexmind.weather;

import pl.hexmind.weather.client.Location;
import pl.hexmind.weather.client.MetaWeatherClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class PlaceQuery implements GraphQLQueryResolver {

    private final MetaWeatherClient metaWeatherClient;

    public PlaceQuery(MetaWeatherClient metaWeatherClient) {
        this.metaWeatherClient = metaWeatherClient;
    }

    @Cacheable("searchPlace")
    public List<Place> searchPlace(String query) {
        return metaWeatherClient.searchLocation(query).stream()
                .filter(l -> l.getLattLong() != null && l.getLattLong().contains(","))
                .map(this::toPlace)
                .collect(Collectors.toList());
    }

    private Place toPlace(Location location) {
        List<BigDecimal> lattLong = Arrays.stream(location.getLattLong().split(","))
                .map(BigDecimal::new)
                .collect(Collectors.toList());
        return Place.builder()
                .id(location.getWoeid())
                .title(location.getTitle())
                .type(location.getLocationType())
                .latitude(lattLong.get(0))
                .longitude(lattLong.get(1))
                .build();
    }
}
