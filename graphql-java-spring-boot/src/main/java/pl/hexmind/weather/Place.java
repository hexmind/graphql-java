package pl.hexmind.weather;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Place {

    private int id;
    private String title;
    private String type;
    private BigDecimal latitude;
    private BigDecimal longitude;

}
