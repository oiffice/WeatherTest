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
public class WeatherCity implements Serializable {

    @JsonProperty("datasetDescription")
    private String dataSetDescription;
    private String locationsName;
    @JsonProperty("dataid")
    private String dataId;
    @JsonProperty("location")
    private List<WeatherLocation> locations;

}
