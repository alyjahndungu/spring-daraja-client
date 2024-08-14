package alyjah.io.daraja.client.integration.stkpush.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MpesaExpressQueryRequest(
        @JsonProperty("BusinessShortCode") String businessShortCode,
        @JsonProperty("Password") String password,
        @JsonProperty("Timestamp") String timestamp,
       @JsonProperty("CheckoutRequestID") String checkoutRequestID
        ) {
}
