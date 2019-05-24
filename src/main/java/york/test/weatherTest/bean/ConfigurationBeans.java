package york.test.weatherTest.bean;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import york.test.weatherTest.config.CWBRequestBuilder;
import york.test.weatherTest.dto.TaiwanDistrictsDTO;
import york.test.weatherTest.enums.CWBConstants;
import york.test.weatherTest.service.CWBRequestService;

import java.io.File;

@Configuration
public class ConfigurationBeans {

    @Bean
    public CWBRequestService cwbRequestClient() {

        return CWBRequestBuilder.create()
                .apiEndPoint(CWBConstants.DEFAULT_API_END_POINT)
                .connectionTimeout(CWBConstants.DEFAULT_CONNECT_TIMEOUT_MILLIS)
                .readTimeout(CWBConstants.DEFAULT_READ_TIMEOUT_MILLIS)
                .writeTimeout(CWBConstants.DEFAULT_WRITE_TIMEOUT_MILLIS)
                .build();
    }

    @Bean("TaiwanDistrictsDTO")
    public TaiwanDistrictsDTO taiwanDistricts() throws Exception {

        File file = ResourceUtils.getFile("classpath:districts.json");
        if (!file.exists()) {
            throw new RuntimeException("Missing Taiwan districts.json");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(file, TaiwanDistrictsDTO.class);
    }
}
