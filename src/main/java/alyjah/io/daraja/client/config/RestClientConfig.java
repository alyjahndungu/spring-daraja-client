package alyjah.io.daraja.client.config;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestOperations;

import javax.net.ssl.SSLContext;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClientSecure() {
        return RestClient
                .builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(
                        HttpClients.custom()
                                .setConnectionManager(secureConnectionManager())
                                .setDefaultRequestConfig(RequestConfig.custom()
                                        .setCookieSpec(StandardCookieSpec.RELAXED)
                                        .build())
                                .build()
                ))
                .build();
    }


    @Bean
    public RestClient restClientInSecure() {
        return RestClient
                .builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(
                        HttpClients.custom()
                                .setConnectionManager(insecureConnectionManager())
                                .setDefaultRequestConfig(RequestConfig.custom()
                                        .setCookieSpec(StandardCookieSpec.RELAXED)
                                        .build())
                                .build()
                ))
                .build();
    }

    @Bean
    public RestOperations buildRestOps(RestTemplateBuilder builder) {
        return builder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(
                        HttpClients.custom()
                                .setConnectionManager(insecureConnectionManager())
                                .setDefaultRequestConfig(RequestConfig.custom()
                                        .setCookieSpec(StandardCookieSpec.RELAXED)
                                        .build())
                                .build()
                ))
                .build();
    }

    private PoolingHttpClientConnectionManager secureConnectionManager() {
        return PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                        .setSslContext(SSLContexts.createSystemDefault())
                        .build())
                .setDefaultSocketConfig(SocketConfig.custom()
                        .setSoTimeout(Timeout.ofMinutes(1))
                        .build())
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setConnPoolPolicy(PoolReusePolicy.LIFO)
                .setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setSocketTimeout(Timeout.ofMinutes(1))
                        .setConnectTimeout(Timeout.ofMinutes(1))
                        .setTimeToLive(TimeValue.ofMinutes(10))
                        .build())
                .build();
    }

    private PoolingHttpClientConnectionManager insecureConnectionManager() {
        return PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                        .setSslContext(sslContext())
                        .build())
                .setDefaultSocketConfig(SocketConfig.custom()
                        .setSoTimeout(Timeout.ofMinutes(1))
                        .build())
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setConnPoolPolicy(PoolReusePolicy.LIFO)
                .setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setSocketTimeout(Timeout.ofMinutes(1))
                        .setConnectTimeout(Timeout.ofMinutes(1))
                        .setTimeToLive(TimeValue.ofMinutes(10))
                        .build())
                .build();
    }

    private SSLContext sslContext() {
        try {
            return SSLContextBuilder
                    .create()
                    .loadTrustMaterial((chain, authType) -> true)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("SslContext exception ", e);
        }
    }
}