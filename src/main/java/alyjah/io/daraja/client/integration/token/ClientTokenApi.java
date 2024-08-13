package alyjah.io.daraja.client.integration.token;


import alyjah.io.daraja.client.integration.base.GenericGetApi;
import alyjah.io.daraja.client.integration.token.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class ClientTokenApi {

    final GenericGetApi genericGetApi;

    public TokenResponse getConsumerAccessToken(
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headers,
            String url
    ) {
        return genericGetApi.makeGetRequest(
                queryParams,
                headers,
                url,
                new ParameterizedTypeReference<>() {
                });
    }

}
