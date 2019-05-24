package york.test.weatherTest.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PushService {

    @Inject
    private WeatherService weatherService;

    @Scheduled(cron = "0 * 8 * * *")
    public void pushWeatherMessageMorning() throws Exception  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        weatherService.fetchWeatherData("臺北市", "中山區", ParseInfoService.WEATHER_DESCRIPTION);
    }
}
