package pl.hexmind.film.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "films", url = "${films.url:http://localhost:8080/v1alpha1/graphql}")
public interface FilmsClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    FilmsResponse searchFilms(@RequestBody GraphqlRequest request);

}
