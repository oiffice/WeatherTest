package york.test.weatherTest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import york.test.weatherTest.bean.ResultBean;
import york.test.weatherTest.dto.WeatherResultDTO;

import javax.inject.Inject;

@Slf4j
@Service
public class WeatherService {

    @Inject
    private RequestCwbService requestCwbService;
    @Inject
    private ParseInfoService parseInfoService;
    @Inject
    private LineService lineService;

    public ResultBean fetchWeatherData(String city, String district, String type) throws Exception {

        WeatherResultDTO response = requestCwbService.request(city);
        log.info("Start to parse {}{} {} data", city, district, type);
        String result = parseInfoService.parse(response, city, district, type);
        lineService.sendResultToLine(result);
        return new ResultBean<>(result);

    }
}
