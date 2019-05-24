package york.test.weatherTest.service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.stereotype.Service;
import york.test.weatherTest.dto.WeatherElement;
import york.test.weatherTest.dto.WeatherResultDTO;
import york.test.weatherTest.dto.WeatherTime;

@Service
public class ParseInfoService {

    public static final String CHANCE_OF_RAIN_12_HOURS = "PoP12h";
    public static final String CHANCE_OF_RAIN_6_HOURS = "PoP6h";
    public static final String WEATHER_DESCRIPTION = "WeatherDescription";
    public static final String TEMPERATURE = "AT";

    public String parse(WeatherResultDTO result, String city, String district, String type) throws Exception {

        if (type.equals(CHANCE_OF_RAIN_6_HOURS) || type.equals(CHANCE_OF_RAIN_12_HOURS)) {
            return this.parsePoP(result, city, district, type);
        } else if (type.equals(WEATHER_DESCRIPTION)) {
            return this.weatherDescription(result, city, district);
        } else if (type.equals(TEMPERATURE)) {
            return this.temperature(result, city, district);
        } else {
            throw new InvalidArgumentException(new String[]{type + " is not available"});
        }
    }

    /**
     * 解析 6 or 12小時降雨機率
     * @return
     */
    private String parsePoP(WeatherResultDTO weatherResult, String city, String district, String interval) {

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
                .filter(districts -> district.equals(districts.getDistrict()))
                .map(location -> {
                    WeatherElement weatherElement = location.getWeatherElements()
                            .stream()
                            .filter(element -> (interval).equals(element.getElementName()))
                            .findFirst()
                            .get();

                    // TODO: maybe other days
                    // today information is index 0
                    WeatherTime weatherTimeStart = weatherElement.getTimes().get(0);

                    return stringBuilder
                            .append(formatOutput(city, district, weatherElement.getDescription()))
                            .append(weatherTimeStart.getStartTime())
                            .append(" - ")
                            .append(weatherTimeStart.getEndTime())
                            .append("\n降雨機率: ")
                            .append(weatherTimeStart.getElementValues().get(0).getValue())
                            .append("%").toString();
                }).findFirst().get();
    }

    /**
     * 解析天氣概況
     * @return
     */
    private String weatherDescription(WeatherResultDTO weatherResult, String city, String district) {
        StringBuilder stringBuilder = new StringBuilder();

        WeatherElement weatherElement = weatherResult
                .getRecords()
                .getCities()
                .stream()
                .filter(weatherCity -> weatherCity.getLocationsName().equals(city))
                .findFirst()
                .get()
                .getLocations()
                .stream()
                .filter(weatherLocation -> weatherLocation.getDistrict().equals(district))
                .findFirst()
                .get()
                .getWeatherElements()
                .stream()
                .filter(element -> element.getElementName().equals(WEATHER_DESCRIPTION))
                .findFirst()
                .get();

        // TODO: time compared
        return stringBuilder
                .append(formatOutput(city, district, weatherElement.getDescription()))
                .append(weatherElement.getTimes().get(0).getStartTime())
                .append(" - ")
                .append(weatherElement.getTimes().get(0).getEndTime())
                .append(weatherElement.getTimes().get(0).getElementValues().get(0).getValue())
                .toString();
    }

    private String temperature(WeatherResultDTO weatherResult, String city, String district) {
        StringBuilder stringBuilder = new StringBuilder();

        WeatherElement weatherElement = weatherResult
                .getRecords()
                .getCities()
                .stream()
                .filter(weatherCity -> weatherCity.getLocationsName().equals(city))
                .findFirst()
                .get()
                .getLocations()
                .stream()
                .filter(weatherLocation -> weatherLocation.getDistrict().equals(district))
                .findFirst()
                .get()
                .getWeatherElements()
                .stream()
                .filter(element -> element.getElementName().equals(TEMPERATURE))
                .findFirst()
                .get();

        String measure = weatherElement.getTimes().get(0).getElementValues().get(0).getMeasures();;
        String descriptionPrefix = measure.substring(0, 2);
        String descriptionPostfix = measure.substring(measure.length() - 1);

        return stringBuilder
                .append(formatOutput(city, district, weatherElement.getDescription()))
                .append(weatherElement.getTimes().get(0).getDataTime())
                .append(" ")
                .append(descriptionPrefix)
                .append(weatherElement.getTimes().get(0).getElementValues().get(0).getValue())
                .append(" ")
                .append(descriptionPostfix)
                .toString();
    }

    private String formatOutput(String city, String district, String description) {
        return new StringBuilder()
                .append("地區:")
                .append(city)
                .append(",")
                .append(district)
                .append("\n")
                .append(description)
                .append("\n時間")
                .toString();
    }
}
