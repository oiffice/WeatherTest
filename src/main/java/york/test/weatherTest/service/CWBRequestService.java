package york.test.weatherTest.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import york.test.weatherTest.dto.WeatherResultDTO;

public interface CWBRequestService {

    /**
     * 未來2天氣象
     * @param authorization
     * @param format
     * @param elements
     * @return
     */
    @GET("{cityRequestCode}")
    Call<WeatherResultDTO> fetchTwoDaysWeather(
            @Path("cityRequestCode") String cityRequestCode,
                                               @Query("Authorization") String authorization,
                                               @Query("elementName")String elements,
                                               @Query("format") String format);


}
