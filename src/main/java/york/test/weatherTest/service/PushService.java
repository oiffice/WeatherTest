package york.test.weatherTest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Slf4j
@Component
public class PushService {

    @Inject
    private WeatherService weatherService;

    @Scheduled(cron = "0 0 8 * * *")
    public void pushWeatherMessageMorning() throws Exception  {
        log.info("Start to push weather information");
        weatherService.fetchWeatherData("新北市", "汐止區", ParseInfoService.WEATHER_DESCRIPTION);
        weatherService.fetchWeatherData("新北市", "汐止區", ParseInfoService.TEMPERATURE);
        weatherService.fetchWeatherData("新北市", "汐止區", ParseInfoService.CHANCE_OF_RAIN_6_HOURS);
    }
}
