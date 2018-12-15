package pl.hexmind.film.client;

import lombok.Data;

@Data
public class Film {
    private long filmId;
    private String title;
    private int releaseYear;
}
