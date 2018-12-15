package pl.hexmind.weather.client;

import lombok.Data;

@Data
public class Location {

    int woeid;
    String title;
    String locationType;
    String lattLong;

}
