package york.test.weatherTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherLocation implements Serializable {

    @JsonProperty("locationName")
    private String district;
    @JsonProperty("geocode")
    private String geoCode;
    private String lat;
    private String lon;
    @JsonProperty("weatherElement")
    private List<WeatherElement> weatherElements;
}
