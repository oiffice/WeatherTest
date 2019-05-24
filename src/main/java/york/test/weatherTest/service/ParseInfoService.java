package york.test.weatherTest.service;

import org.springframework.stereotype.Service;
import york.test.weatherTest.dto.WeatherElement;
import york.test.weatherTest.dto.WeatherResultDTO;
import york.test.weatherTest.dto.WeatherTime;

@Service
public class ParseInfoService {

    public static final String CHANCE_OF_RAIN_12_HOURS = "PoP12h";
    public static final String CHANCE_OF_RAIN_6_HOURS = "PoP6h";

    /**
     * 解析12小時降雨機率
     * @return
     */
    public String parsePoP(WeatherResultDTO weatherResult, String city, String district, String interval) {

        // TODO: only taipei currently
        StringBuilder stringBuilder = new StringBuilder();

        return weatherResult
                .getRecords()
                .getCities()
                .stream()
                .filter(weatherCity -> city.equals(weatherCity.getLocationsName()))
                .findFirst()
                .get()
                .getLocations()
                .stream()
                .filter(districts -> district.equals(districts.getDistricts()))
                .map(location -> {
                    WeatherElement weatherElement = location.getWeatherElements()
                            .stream()
                            .filter(element -> (interval).equals(element.getElementName()))
                            .findFirst()
                            .get();

                    // today information is index 0
                    WeatherTime weatherTimeStart = weatherElement.getTimes().get(0);

                    String pop = stringBuilder
                            .append("時間:")
                            .append(weatherTimeStart.getStartTime())
                            .append(" - ")
                            .append(weatherTimeStart.getEndTime())
                            .append("\n降雨機率: ")
                            .append(weatherTimeStart.getElementValues().get(0).getValue())
                            .append("%").toString();

                    // reset string builder
                    stringBuilder.setLength(0);

                    // TODO: time compared?
                    return stringBuilder
                            .append("地區:").append(city).append(",").append(location.getDistricts()).append("\n")
                            .append(weatherElement.getDescription())
                            .append(pop)
                            .toString();

                }).findFirst().get();
    }
}
