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
import com.google.common.base.Splitter;

@Component
public class PlaceResolver implements GraphQLResolver<Place> {

    private final MetaWeatherClient metaMetaWeatherClient;
    private final FilmsClient filmsClient;
    private String searchFilmsQuery;

    public PlaceResolver(MetaWeatherClient metaMetaWeatherClient,
                         FilmsClient filmsClient,
                         @Value("classpath:queries/searchFilms.graphqls") Resource searchFilmsQuery
    ) throws IOException {
        this.metaMetaWeatherClient = metaMetaWeatherClient;
        this.filmsClient = filmsClient;
        this.searchFilmsQuery = StreamUtils.copyToString(searchFilmsQuery.getInputStream(), Charsets.UTF_8);
    }

    @Cacheable("getForecast")
    public Forecast getForecast(Place place) {
        return metaMetaWeatherClient.getForecast(place.getId());
    }

    public List<Film> getFilms(Place place, DataFetchingEnvironment environment) {
        String pattern = Splitter.fixedLength(3).split(place.getTitle())
                .iterator().next()
                .toUpperCase() + "%";
        String query = searchFilmsQuery.replace("$pattern", pattern);
        return filmsClient
                .searchFilms(new GraphqlRequest(query))
                .getData()
                .getFilm();
    }

}
