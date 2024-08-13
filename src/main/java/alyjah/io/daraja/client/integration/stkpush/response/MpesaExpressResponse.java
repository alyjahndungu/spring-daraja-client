package alyjah.io.daraja.client.integration.stkpush.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MpesaExpressResponse(
        @JsonProperty("MerchantRequestID") String merchantRequestID,
        @JsonProperty("ResponseCode") String responseCode,
        @JsonProperty("CustomerMessage") String customerMessage,
        @JsonProperty("CheckoutRequestID") String checkoutRequestID,
        @JsonProperty("ResponseDescription") String responseDescription
) {
}
