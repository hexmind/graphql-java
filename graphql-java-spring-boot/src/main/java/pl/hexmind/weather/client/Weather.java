package pl.hexmind.weather.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Weather {

    private long id;
    private String weatherStateName;
    private String weatherStateAbbr;
    private String windDirectionCompass;
    private LocalDateTime created;
    private LocalDate applicableDate;
    private BigDecimal minTemp;
    private BigDecimal maxTemp;
    private BigDecimal theTemp;
    private BigDecimal windSpeed;
    private BigDecimal windDirection;
    private BigDecimal airPressure;
    private BigDecimal humidity;
    private BigDecimal visibility;
    private int predictability;

}
