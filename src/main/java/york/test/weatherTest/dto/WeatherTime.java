package york.test.weatherTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WeatherTime implements Serializable {

    private String startTime;
    private String endTim;
    @JsonProperty("elementValue")
    private List<WeatherElementValue> elementValues;
}
