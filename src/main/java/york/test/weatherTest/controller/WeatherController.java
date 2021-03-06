package york.test.weatherTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import york.test.weatherTest.bean.ResultBean;
import york.test.weatherTest.service.ParseInfoService;
import york.test.weatherTest.service.WeatherService;

import javax.inject.Inject;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Inject
    private WeatherService weatherService;

    @RequestMapping("/today/12/rain")
    public ResultBean fetchToday12Rain(@RequestParam String cityInEn, @RequestParam String districts) throws Exception {
        return weatherService.fetchWeatherData(cityInEn, districts, ParseInfoService.CHANCE_OF_RAIN_12_HOURS);
    }

    @RequestMapping("/today/6/rain")
    public ResultBean fetchToday6Rain(@RequestParam String cityInEn, @RequestParam String districts) throws Exception {
        return weatherService.fetchWeatherData(cityInEn, districts, ParseInfoService.CHANCE_OF_RAIN_6_HOURS);
    }

    @RequestMapping("/today/description")
    public ResultBean fetchTodayWeatherDesc(@RequestParam String cityInEn, @RequestParam String districts) throws Exception {
        return weatherService.fetchWeatherData(cityInEn, districts, ParseInfoService.WEATHER_DESCRIPTION);
    }

    @RequestMapping("/today/temperature")
    public ResultBean fetchTodayTemperature(@RequestParam String cityInEn, @RequestParam String districts) throws Exception {
        return weatherService.fetchWeatherData(cityInEn, districts, ParseInfoService.TEMPERATURE);
    }

    @RequestMapping("/sevenDays")
    public ResultBean fetchSevenDays(@RequestParam String cityInEn) {
        return new ResultBean<>();
    }
}
