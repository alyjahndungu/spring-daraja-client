package alyjah.io.daraja.client.integration.stkpush.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StkCallback(
        @JsonProperty("MerchantRequestID")
        String merchantRequestID,

        @JsonProperty("CheckoutRequestID")
        String checkoutRequestID,

        @JsonProperty("ResultCode")
        int resultCode,

        @JsonProperty("ResultDesc")
        String resultDesc,

        @JsonProperty("CallbackMetadata")
        CallbackMetadata callbackMetadata
) {
}
