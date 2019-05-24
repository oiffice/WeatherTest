package york.test.weatherTest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import york.test.weatherTest.enums.CWBConstants;
import york.test.weatherTest.service.CWBRequestService;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class CWBRequestBuilder {

    private Retrofit.Builder retrofitBuilder;
    private String apiEndPoint = CWBConstants.DEFAULT_API_END_POINT;
    private long connectionTimeout = CWBConstants.DEFAULT_CONNECT_TIMEOUT_MILLIS;
    private long readTimeout = CWBConstants.DEFAULT_READ_TIMEOUT_MILLIS;
    private long writeTimeout = CWBConstants.DEFAULT_WRITE_TIMEOUT_MILLIS;

    private OkHttpClient.Builder okHttpClientBuilder;

    private CWBRequestBuilder(){}

    public static CWBRequestBuilder create() {
        return new CWBRequestBuilder();
    }

    public CWBRequestBuilder connectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public CWBRequestBuilder readTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public CWBRequestBuilder writeTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public CWBRequestBuilder apiEndPoint(String apiEndPoint) {
        this.apiEndPoint = apiEndPoint;
        return this;
    }

    private static Retrofit.Builder createRetrofitBuilder() {
        final ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // Register ParameterNamesModule to read parameter name from lombok generated constructor.
                .registerModule(new ParameterNamesModule())
                // Register JSR-310(java.time.temporal.*) module and read number as millsec.
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);

        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper));
    }

    private static Interceptor buildLoggingInterceptor() {
        final Logger slf4jLogger = LoggerFactory.getLogger("york.test.weatherTest.weather");

        return new HttpLoggingInterceptor(slf4jLogger::info)
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public CWBRequestService build() {

        if (okHttpClientBuilder == null) {
            okHttpClientBuilder = new OkHttpClient.Builder();
        }

        okHttpClientBuilder
                .addInterceptor(buildLoggingInterceptor())
                .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);

        final OkHttpClient okHttpClient = okHttpClientBuilder.sslSocketFactory(createSSLSocketFactory()).build();


        if (retrofitBuilder == null) {
            retrofitBuilder = createRetrofitBuilder();
        }

        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.baseUrl(apiEndPoint);
        final Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(CWBRequestService.class);
    }

    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            MyTrustManager mMyTrustManager = new MyTrustManager();
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{mMyTrustManager}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ssfFactory;
    }

    public static class MyTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

}
