package alyjah.io.daraja.client.integration.base;

import alyjah.io.daraja.client.util.Helpers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
public class GenericDeleteApi {

    final RestClient restClient;

    public GenericDeleteApi(@Qualifier(value = "restClientInSecure") RestClient restClient) {
        this.restClient = restClient;
    }
    private RestClient.ResponseSpec prepareRequest(
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headers,
            String url
    ) {
        return restClient.delete()
                .uri(url, builder -> builder.queryParams(queryParams).build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
    }

    public <R> ResponseEntity<Void> genericDeleteVoidRequest(
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headers,
            String url
    ) {
        return prepareRequest(queryParams, headers, url)
                .onStatus(HttpStatusCode::isError, (HttpRequest request, ClientHttpResponse response) -> Helpers.handleHttpError(response))
                .toBodilessEntity();
    }
}
