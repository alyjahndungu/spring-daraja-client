package alyjah.io.daraja.client.integration.stkpush.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record MpesaExpressRequest(
        @JsonProperty("TransactionType") String transactionType,
        @JsonProperty("Amount") BigDecimal amount,
        @JsonProperty("CallBackURL") String callBackURL,
        @JsonProperty("PhoneNumber") String phoneNumber,
        @JsonProperty("PartyA") String partyA,
        @JsonProperty("PartyB") String partyB,
        @JsonProperty("AccountReference") String accountReference,
        @JsonProperty("TransactionDesc") String transactionDesc,
        @JsonProperty("BusinessShortCode") String businessShortCode,
        @JsonProperty("Timestamp") String timestamp,
        @JsonProperty("Password") String password
) {
}
