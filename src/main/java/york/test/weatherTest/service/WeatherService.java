package york.test.weatherTest.service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import york.test.weatherTest.bean.ResultBean;
import york.test.weatherTest.config.WeatherProperties;
import york.test.weatherTest.dto.WeatherResultDTO;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

@Service
@EnableConfigurationProperties(WeatherProperties.class)
public class WeatherService {

    @Inject
    private RequestCwbService requestCwbService;
    @Inject
    private ParseInfoService parseInfoService;

    /**
     * 取得降雨機率資料
     * @param city
     * @param district
     * @param timeInterval
     * @return
     * @throws Exception
     */
    public ResultBean fetchTodayRain(String city, String district, String timeInterval) throws Exception {

        if (!timeInterval.equals(ParseInfoService.CHANCE_OF_RAIN_6_HOURS) &&
                !timeInterval.equals(ParseInfoService.CHANCE_OF_RAIN_12_HOURS)) {

            throw new InvalidArgumentException(new String[]{timeInterval + " is not available"});

        }

        WeatherResultDTO response = requestCwbService.request(city);
        return new ResultBean<>(parseInfoService.parsePoP(response, city, district, timeInterval));
    }

    public ResultBean fetchTodayWeatherDesc(String city, String district) throws Exception {

        WeatherResultDTO response = requestCwbService.request(city);
        return new ResultBean<>(parseInfoService.weatherDescription(response, city, district));
    }
}
