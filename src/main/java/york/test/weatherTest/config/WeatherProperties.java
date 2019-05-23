package york.test.weatherTest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "cwb")
public class WeatherProperties {

    @NotNull
    private String token;
    @NotNull
    private Map<String, String> query;

}
