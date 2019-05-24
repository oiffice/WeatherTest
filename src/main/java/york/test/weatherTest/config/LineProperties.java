package york.test.weatherTest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "line")
public class LineProperties {

    private List<String> ids;
}
