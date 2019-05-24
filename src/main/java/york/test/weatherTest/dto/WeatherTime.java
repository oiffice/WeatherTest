package york.test.weatherTest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherTime implements Serializable {

    private String dataTime;
    private String startTime;
    private String endTime;
    @JsonProperty("elementValue")
    private List<WeatherElementValue> elementValues;
}
