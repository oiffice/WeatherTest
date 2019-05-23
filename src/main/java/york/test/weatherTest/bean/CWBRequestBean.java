package york.test.weatherTest.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import york.test.weatherTest.CWBConstants;
import york.test.weatherTest.CWBRequestBuilder;
import york.test.weatherTest.CWBRequestService;

@Configuration
public class CWBRequestBean {

    @Bean
    public CWBRequestService cwbRequestClient() {

        return CWBRequestBuilder.create()
                .apiEndPoint(CWBConstants.DEFAULT_API_END_POINT)
                .connectionTimeout(CWBConstants.DEFAULT_CONNECT_TIMEOUT_MILLIS)
                .readTimeout(CWBConstants.DEFAULT_READ_TIMEOUT_MILLIS)
                .writeTimeout(CWBConstants.DEFAULT_WRITE_TIMEOUT_MILLIS)
                .build();
    }
}
