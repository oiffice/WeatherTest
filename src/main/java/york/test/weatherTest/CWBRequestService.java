package york.test.weatherTest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import york.test.weatherTest.dto.WeatherResultDTO;

public interface CWBRequestService {

    /**
     * Taipei
     * @param authorization
     * @param format
     * @param elements
     * @return
     */
    @GET("F-D0047-061")
    Call<WeatherResultDTO> fetchTaipeiTwoDays(@Query("Authorization") String authorization,
                                              @Query("format") String format, @Query("elementName")String elements);


}
