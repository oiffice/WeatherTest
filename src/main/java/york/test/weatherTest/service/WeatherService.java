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
import java.util.Map;

@Service
@EnableConfigurationProperties(WeatherProperties.class)
public class WeatherService {

    @Inject
    private CWBRequestService cwbRequestService;
    @Inject
    private WeatherProperties weatherProperties;
    @Inject
    private ParseInfoService parseInfoService;

    /**
     * @param city
     * @return
     */
    public ResultBean fetchTodayRain(String city, String districts, String timeInterval) throws Exception {

        if (!timeInterval.equals(ParseInfoService.CHANCE_OF_RAIN_6_HOURS) &&
                !timeInterval.equals(ParseInfoService.CHANCE_OF_RAIN_12_HOURS)) {

            throw new InvalidArgumentException(new String[]{timeInterval + " is not available"});

        }

        Map<String, String> query = weatherProperties.getQuery();
        Call<WeatherResultDTO> resultDTOCall =
                cwbRequestService.fetchTaipeiTwoDays(weatherProperties.getToken(), query.get("format"), query.get("elements"));

        Response<WeatherResultDTO> response = resultDTOCall.execute();

        if (!response.isSuccessful()) {
            return new ResultBean<>(new Exception("Request weather data failed: " + response.errorBody()));
        } else if (response.body() == null) {
            return new ResultBean<>("No data can present");
        } else {
            return new ResultBean<>(parseInfoService.parsePoP(response.body(), city, districts, timeInterval));
        }
    }
}
