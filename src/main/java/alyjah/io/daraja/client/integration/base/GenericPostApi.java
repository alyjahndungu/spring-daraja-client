package alyjah.io.daraja.client.integration.base;

import alyjah.io.daraja.client.util.Helpers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
public class GenericPostApi {

    final RestClient restClient;

    public GenericPostApi(@Qualifier(value = "restClientInSecure") RestClient restClient) {
        this.restClient = restClient;
    }

    private <R> RestClient.ResponseSpec prepareRequest(
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headers,
            String url,
            R requestBody
    ) {
        return restClient.method(HttpMethod.POST)
                .uri(url, builder -> builder.queryParams(queryParams).build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .accept(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve();
    }

    public <T, R> T genericPostRequest(
            R requestBody,
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headers,
            String url,
            ParameterizedTypeReference<T> responseType
    ) {
        return prepareRequest(queryParams, headers, url, requestBody)
                .onStatus(HttpStatusCode::isError, (HttpRequest request, ClientHttpResponse response) -> Helpers.handleHttpError(response))
                .body(responseType);
    }

    public <R> ResponseEntity<Void> genericPostVoidRequest(
            R requestBody,
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headers,
            String url
    ) {
        return prepareRequest(queryParams, headers, url, requestBody)
                .onStatus(HttpStatusCode::isError, (HttpRequest request, ClientHttpResponse response) -> Helpers.handleHttpError(response))
                .toBodilessEntity();
    }
}
