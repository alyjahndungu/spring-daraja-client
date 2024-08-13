package alyjah.io.daraja.client.util;

import alyjah.io.daraja.client.config.DarajaConfig;
import alyjah.io.daraja.client.integration.common.CommonBuilder;
import alyjah.io.daraja.client.integration.token.ClientTokenApi;
import alyjah.io.daraja.client.integration.token.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class DarajaUtil {

    private final ClientTokenApi clientTokenApi;
    private final CommonBuilder commonBuilder;
    private final DarajaConfig darajaConfig;

    public TokenResponse getClientToken() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("grant_type", darajaConfig.getGrantType());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.addAll(commonBuilder.buildBasicAuthorizationHeader(darajaConfig.getConsumerKey(), darajaConfig.getConsumerSecret()));
        return clientTokenApi.getConsumerAccessToken(
              queryParams,
                headers,
                darajaConfig.getOauthEndpoint()
        );
    }
}
