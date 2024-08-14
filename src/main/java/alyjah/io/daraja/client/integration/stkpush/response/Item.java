package alyjah.io.daraja.client.integration.stkpush.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Item(
        @JsonProperty("Name")
        String name,

        @JsonProperty("Value")
        Object value) {
}

