package york.test.weatherTest.service;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import york.test.weatherTest.config.WeatherProperties;
import york.test.weatherTest.dto.WeatherResultDTO;
import york.test.weatherTest.enums.Cities;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;

@Service
public class RequestCwbService {

    @Inject
    private WeatherProperties weatherProperties;
    @Inject
    private CWBRequestService cwbRequestService;

    public WeatherResultDTO request(String city) throws Exception {

        String requestCode = Arrays.stream(Cities.values())
                .filter(enumCity -> city.equals(enumCity.toString()))
                .map(Cities::getRequestCode)
                .findFirst()
                .get();

        Map<String, String> query = weatherProperties.getQuery();
        Call<WeatherResultDTO> resultDTOCall =
                cwbRequestService.fetchTwoDaysWeather(requestCode, weatherProperties.getToken(), query.get("elements"),query.get("format"));

        Response<WeatherResultDTO> response = resultDTOCall.execute();

        if (!response.isSuccessful()) {
            throw new Exception("Request weather data failed: " + response.errorBody());
        } else if (response.body() == null) {
            throw new NullPointerException("No data can present");
        }

        return response.body();
    }
}
