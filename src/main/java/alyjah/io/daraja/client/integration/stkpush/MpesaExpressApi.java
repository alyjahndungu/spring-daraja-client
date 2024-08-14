package alyjah.io.daraja.client.integration.stkpush;

import alyjah.io.daraja.client.integration.base.GenericPostApi;
import alyjah.io.daraja.client.integration.stkpush.request.MpesaExpressQueryRequest;
import alyjah.io.daraja.client.integration.stkpush.request.MpesaExpressRequest;
import alyjah.io.daraja.client.integration.stkpush.response.MpesaExpressQueryResponse;
import alyjah.io.daraja.client.integration.stkpush.response.MpesaExpressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class MpesaExpressApi {

    private final GenericPostApi genericPostApi;

    public MpesaExpressResponse simulateMpesaExpressTransaction(
            MpesaExpressRequest request,
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headers,
            String url
    ) {
        return genericPostApi.genericPostRequest(
                request,
                queryParams,
                headers,
                url,
                new ParameterizedTypeReference<>() {
                });
    }

    public MpesaExpressQueryResponse queryMpesaExpressTransaction(
            MpesaExpressQueryRequest request,
            MultiValueMap<String, String> queryParams,
            MultiValueMap<String, String> headers,
            String url
    ) {
        return genericPostApi.genericPostRequest(
                request,
                queryParams,
                headers,
                url,
                new ParameterizedTypeReference<>() {
                });
    }
}
