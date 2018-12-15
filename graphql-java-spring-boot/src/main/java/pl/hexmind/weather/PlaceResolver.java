package pl.hexmind.weather;

import graphql.schema.DataFetchingEnvironment;
import pl.hexmind.film.client.Film;
import pl.hexmind.film.client.FilmsClient;
import pl.hexmind.film.client.GraphqlRequest;
import pl.hexmind.weather.client.Forecast;
import pl.hexmind.weather.client.MetaWeatherClient;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.google.common.base.Charsets;

@Component
public class PlaceResolver implements GraphQLResolver<Place> {

    private final MetaWeatherClient metaWeatherClient;
    private final FilmsClient filmsClient;
    private String searchFilmsQuery;

    public PlaceResolver(MetaWeatherClient metaWeatherClient,
                         FilmsClient filmsClient,
                         @Value("classpath:queries/searchFilms.graphqls") Resource searchFilmsQuery
    ) throws IOException {
        this.metaWeatherClient = metaWeatherClient;
        this.filmsClient = filmsClient;
        this.searchFilmsQuery = StreamUtils.copyToString(searchFilmsQuery.getInputStream(), Charsets.UTF_8);
    }

    @Cacheable("getForecast")
    public Forecast getForecast(Place place) {
        return metaWeatherClient.getForecast(place.getId());
    }

    public List<Film> getFilms(Place place, DataFetchingEnvironment environment) {
        String query = searchFilmsQuery.replace(
                "$pattern",
                place.getTitle().charAt(0) + "%"
        );
        return filmsClient
                .searchFilms(new GraphqlRequest(query))
                .getData()
                .getFilm();
    }

}
