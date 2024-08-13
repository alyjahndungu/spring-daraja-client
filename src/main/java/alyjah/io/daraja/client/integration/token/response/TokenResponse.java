package alyjah.io.daraja.client.integration.token.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TokenResponse(
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("expires_in") String expiresIn
) {
}
