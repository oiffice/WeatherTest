package york.test.weatherTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import york.test.weatherTest.config.WeatherProperties;

@EnableConfigurationProperties(value = WeatherProperties.class)
@SpringBootApplication
public class WeatherTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherTestApplication.class, args);
	}

}
