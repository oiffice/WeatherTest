package york.test.weatherTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResultDTO implements Serializable {

    private String success;
    private WeatherRecord records;
}
