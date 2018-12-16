package pl.hexmind.film;

import graphql.schema.DataFetchingEnvironment;
import pl.hexmind.common.StringUtil;
import pl.hexmind.film.client.Film;
import pl.hexmind.film.client.FilmsClient;
import pl.hexmind.common.graphql.GraphqlRequest;
import pl.hexmind.common.graphql.SelectionSets;
import pl.hexmind.weather.Place;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.google.common.base.Charsets;

@Service
public class FilmsService {

    private final FilmsClient filmsClient;
    private final String searchFilmsQuery;

    public FilmsService(FilmsClient filmsClient,
                        @Value("classpath:queries/searchFilms.query") Resource searchFilmsQuery) throws IOException {
        this.filmsClient = filmsClient;
        this.searchFilmsQuery = StreamUtils.copyToString(searchFilmsQuery.getInputStream(), Charsets.UTF_8);
    }

    public List<Film> searchFilms(Place place, DataFetchingEnvironment environment) {
        return filmsClient
                .searchFilms(toSearchRequest(place, environment))
                .getData()
                .getFilm();
    }

    private GraphqlRequest toSearchRequest(Place place, DataFetchingEnvironment environment) {
        String pattern = StringUtil.left(place.getTitle(), 3).toUpperCase() + "%";
        String selections = SelectionSets.join("films", environment);
        String query = searchFilmsQuery
                .replace("$pattern", pattern)
                .replace("$selections", StringUtil.toUnderscore(selections));
        return new GraphqlRequest(query);
    }

}
