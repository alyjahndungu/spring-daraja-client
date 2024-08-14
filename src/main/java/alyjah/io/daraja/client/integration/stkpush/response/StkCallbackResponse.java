package alyjah.io.daraja.client.integration.stkpush.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StkCallbackResponse(
        @JsonProperty("Body") Body body
) {
}
