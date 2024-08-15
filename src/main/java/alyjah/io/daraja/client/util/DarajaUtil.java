package alyjah.io.daraja.client.util;

import alyjah.io.daraja.client.business.ExpressTransactionService;
import alyjah.io.daraja.client.config.DarajaConfig;
import alyjah.io.daraja.client.integration.common.CommonBuilder;
import alyjah.io.daraja.client.integration.stkpush.MpesaExpressApi;
import alyjah.io.daraja.client.integration.stkpush.request.MpesaExpressQueryRequest;
import alyjah.io.daraja.client.integration.stkpush.request.MpesaExpressRequest;
import alyjah.io.daraja.client.integration.stkpush.response.MpesaExpressQueryResponse;
import alyjah.io.daraja.client.integration.stkpush.response.MpesaExpressResponse;
import alyjah.io.daraja.client.integration.token.ClientTokenApi;
import alyjah.io.daraja.client.integration.token.response.TokenResponse;
import alyjah.io.daraja.client.payload.request.ExpressPaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class DarajaUtil {

    private final ClientTokenApi clientTokenApi;
    private final MpesaExpressApi mpesaExpressApi;
    private final CommonBuilder commonBuilder;
    private final DarajaConfig darajaConfig;
    private final ExpressTransactionService expressTransactionService;


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
         String accountReference = Helpers.getSecureUniqueString();
         String transactionTimestamp= Helpers.getTransactionTimestamp();

        MpesaExpressResponse response = mpesaExpressApi.simulateMpesaExpressTransaction(
                new MpesaExpressRequest(ETransactionType.CUSTOMER_PAYBILL_ONLINE.getType(), request.amount(), darajaConfig.getStkPushCallbackUrl(), request.phoneNumber(), request.phoneNumber(), darajaConfig.getStkPushShortCode(), accountReference, "Online Payment", darajaConfig.getStkPushShortCode(), transactionTimestamp, encodedPassword),
                new LinkedMultiValueMap<>(),
                headers,
                darajaConfig.getStkPushEndpoint()
        );

        expressTransactionService.save(response.checkoutRequestID(), response.merchantRequestID(), request.amount(), request.phoneNumber(), accountReference, transactionTimestamp);
        return response;
    }

    public MpesaExpressQueryResponse queryMpesaExpressTransaction(String businessShortCode, String password, String timestamp, String checkoutRequestId) {
        TokenResponse tokenResponse = getClientToken();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.addAll(commonBuilder.buildBearerAuthorizationHeader(tokenResponse.accessToken()));
        headers.addAll(commonBuilder.buildContentTypeHeader(MediaType.APPLICATION_JSON_VALUE));

        return mpesaExpressApi.queryMpesaExpressTransaction(
                new MpesaExpressQueryRequest(businessShortCode, password, timestamp, checkoutRequestId),
                new LinkedMultiValueMap<>(),
                headers,
                darajaConfig.getStkPushEndpoint()
        );
    }
}
