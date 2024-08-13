package alyjah.io.daraja.client.util;

import alyjah.io.daraja.client.config.DarajaConfig;
import alyjah.io.daraja.client.integration.common.CommonBuilder;
import alyjah.io.daraja.client.integration.stkpush.MpesaExpressApi;
import alyjah.io.daraja.client.integration.stkpush.request.StkPushRequest;
import alyjah.io.daraja.client.integration.stkpush.response.MpesaExpressResponse;
import alyjah.io.daraja.client.integration.token.ClientTokenApi;
import alyjah.io.daraja.client.integration.token.response.TokenResponse;
import alyjah.io.daraja.client.payload.request.ExpressPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class DarajaUtil {

    private final ClientTokenApi clientTokenApi;
    private final MpesaExpressApi mpesaExpressApi;
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


    public MpesaExpressResponse simulateMpesaExpressTransaction(ExpressPaymentRequest request) {
        TokenResponse tokenResponse = getClientToken();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.addAll(commonBuilder.buildBearerAuthorizationHeader(tokenResponse.accessToken()));
        headers.addAll(commonBuilder.buildContentTypeHeader(MediaType.APPLICATION_JSON_VALUE));

        String encodedPassword = Helpers.encodePasswordToBase64(darajaConfig.getStkPushShortCode(), darajaConfig.getStkPushPasskey());

        return mpesaExpressApi.initiateLipaNaMpesaOnline(
                new StkPushRequest(ETransactionType.CUSTOMER_PAYBILL_ONLINE.getType(), request.amount(), darajaConfig.getStkPushCallbackUrl(), request.phoneNumber(),  request.phoneNumber(), darajaConfig.getStkPushShortCode(), Helpers.getSecureUniqueString(), request.description(), darajaConfig.getStkPushShortCode(), Helpers.getTransactionTimestamp(),  encodedPassword),
                new LinkedMultiValueMap<>(),
                headers,
                darajaConfig.getStkPushEndpoint()
        );
    }
}
