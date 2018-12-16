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
import com.google.common.base.Strings;

@Component
public class PlaceQuery implements GraphQLQueryResolver {

    private final MetaWeatherClient metaMetaWeatherClient;

    public PlaceQuery(MetaWeatherClient metaMetaWeatherClient) {
        this.metaMetaWeatherClient = metaMetaWeatherClient;
    }

    @Cacheable("searchPlace")
    public List<Place> searchPlace(String query) {
        return metaMetaWeatherClient.searchLocation(query).stream()
                .map(this::toPlace)
                .collect(Collectors.toList());
    }

    private Place toPlace(Location location) {
        List<BigDecimal> lattLong = splitLattLong(location.getLattLong());
        return Place.builder()
                .id(location.getWoeid())
                .title(location.getTitle())
                .type(location.getLocationType())
                .latitude(lattLong.get(0))
                .longitude(lattLong.get(1))
                .build();
    }

    private List<BigDecimal> splitLattLong(String lattLong) {
        if (!Strings.isNullOrEmpty(lattLong)
                && lattLong.contains(",")) {
            return Arrays.stream(lattLong.split(","))
                    .map(BigDecimal::new)
                    .collect(Collectors.toList());
        } else {
            return Arrays.asList(null, null);
        }
    }
}
