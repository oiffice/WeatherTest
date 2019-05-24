package york.test.weatherTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaiwanDistricts {

    @JsonProperty("districts")
    private List<District> districts;
    @JsonProperty("name")
    private String cityName;
}
