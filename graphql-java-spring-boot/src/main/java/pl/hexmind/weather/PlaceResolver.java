package pl.hexmind.weather;

import graphql.schema.DataFetchingEnvironment;
import pl.hexmind.film.FilmsService;
import pl.hexmind.film.client.Film;
import pl.hexmind.weather.client.Forecast;
import pl.hexmind.weather.client.MetaWeatherClient;

import java.io.IOException;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class PlaceResolver implements GraphQLResolver<Place> {

    private final MetaWeatherClient metaMetaWeatherClient;
    private final FilmsService filmsService;

    public PlaceResolver(MetaWeatherClient metaMetaWeatherClient,
                         FilmsService filmsService) throws IOException {
        this.metaMetaWeatherClient = metaMetaWeatherClient;
        this.filmsService = filmsService;
    }

    @Cacheable("getForecast")
    public Forecast getForecast(Place place) {
        return metaMetaWeatherClient.getForecast(place.getId());
    }

    public List<Film> getFilms(Place place, DataFetchingEnvironment environment) {
        return filmsService.searchFilms(place, environment);
    }

}
