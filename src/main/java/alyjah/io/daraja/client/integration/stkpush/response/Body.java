package alyjah.io.daraja.client.integration.stkpush.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Body(@JsonProperty("stkCallback") StkCallback stkCallback) {}