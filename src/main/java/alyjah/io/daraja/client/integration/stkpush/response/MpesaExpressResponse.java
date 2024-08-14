package alyjah.io.daraja.client.integration.stkpush.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MpesaExpressResponse(
        @JsonProperty("ResponseCode") String responseCode,
        @JsonProperty("ResponseDescription") String responseDescription,
        @JsonProperty("CheckoutRequestID") String checkoutRequestID,
        @JsonProperty("MerchantRequestID") String merchantRequestID,
        @JsonProperty("ResultCode") String resultCode,
        @JsonProperty("ResultDesc") String resultDesc
        ) {
}
