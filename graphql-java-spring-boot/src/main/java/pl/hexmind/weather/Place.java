package pl.hexmind.weather;

import lombok.Builder;
import lombok.Data;
import pl.hexmind.weather.client.Forecast;

import java.math.BigDecimal;

@Data
@Builder
public class Place {

    String id;
    String title;
    String type;
    BigDecimal latitude;
    BigDecimal longitude;
    Forecast forecast;
}
