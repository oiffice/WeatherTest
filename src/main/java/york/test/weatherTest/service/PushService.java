package york.test.weatherTest.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PushService {

    @Inject
    private WeatherService weatherService;

    @Scheduled(cron = "0 * * * * *")
    public void pushWeatherMessageMorning() throws Exception  {
        weatherService.fetchWeatherData("新北市", "汐止區", ParseInfoService.WEATHER_DESCRIPTION);
    }
}
