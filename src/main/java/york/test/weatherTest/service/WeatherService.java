package york.test.weatherTest.service;

import org.springframework.stereotype.Service;
import york.test.weatherTest.bean.ResultBean;
import york.test.weatherTest.dto.WeatherResultDTO;

import javax.inject.Inject;

@Service
public class WeatherService {

    @Inject
    private RequestCwbService requestCwbService;
    @Inject
    private ParseInfoService parseInfoService;

    public ResultBean fetchWeatherData(String city, String district, String type) throws Exception {

        WeatherResultDTO response = requestCwbService.request(city);
        return new ResultBean<>(parseInfoService.parse(response, city, district, type));

    }
}
