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
public class WeatherElement implements Serializable {

    private String elementName;
    private String description;
    @JsonProperty("time")
    private List<WeatherTime> times;
}
