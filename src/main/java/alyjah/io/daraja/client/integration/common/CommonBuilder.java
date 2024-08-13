package alyjah.io.daraja.client.integration.common;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class CommonBuilder {

    public MultiValueMap<String, String> buildBearerAuthorizationHeader(String token) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(HttpHeaders.AUTHORIZATION, "Bearer ".concat(token));
        return map;
    }


    public MultiValueMap<String, String> buildContentTypeHeader(String mediaType) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(HttpHeaders.CONTENT_TYPE, mediaType);
        return map;
    }

    public MultiValueMap<String, String> buildBasicAuthorizationHeader(String consumerKey, String consumerSecret) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(HttpHeaders.AUTHORIZATION, "Basic ".concat(Base64.getEncoder().encodeToString((consumerKey + ":" + consumerKey).getBytes(UTF_8))));
        return map;
    }
}
