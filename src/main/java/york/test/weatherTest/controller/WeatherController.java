package york.test.weatherTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import york.test.weatherTest.WeatherService;
import york.test.weatherTest.bean.ResultBean;

import javax.inject.Inject;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Inject
    private WeatherService weatherService;

    @RequestMapping("/today")
    public ResultBean fetchToday(@RequestParam String cityInEn) throws Exception {
        return weatherService.fetchToday(cityInEn);
    }

    @RequestMapping("/sevenDays")
    public ResultBean fetchSevenDays(@RequestParam String cityInEn) {
        return new ResultBean<>();
    }
}
