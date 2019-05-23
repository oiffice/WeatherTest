package york.test.weatherTest;

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

    /**
     * TODO: current only fetch taipei information
     * @param city
     * @return
     */
    public ResultBean fetchToday(String city) throws Exception {

        Map<String, String> query = weatherProperties.getQuery();
        Call<WeatherResultDTO> resultDTOCall =
                cwbRequestService.fetchTaipeiTwoDays(weatherProperties.getToken(), query.get("format"), query.get("elements"));

        Response<WeatherResultDTO> response = resultDTOCall.execute();

        if (!response.isSuccessful()) {
            return new ResultBean<>(new Exception("Request weather data failed: " + response.errorBody()));
        }

        return new ResultBean<>(response.body());
    }
}
