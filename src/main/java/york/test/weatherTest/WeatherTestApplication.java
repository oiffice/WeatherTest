package york.test.weatherTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import york.test.weatherTest.config.LineProperties;
import york.test.weatherTest.config.WeatherProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {WeatherProperties.class, LineProperties.class})
public class WeatherTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherTestApplication.class, args);
	}
}
