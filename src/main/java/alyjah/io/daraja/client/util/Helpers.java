package alyjah.io.daraja.client.util;

import camacuchi.keycloak.proxy.exception.BadRequestException;
import camacuchi.keycloak.proxy.exception.GatewayException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static camacuchi.keycloak.proxy.util.ErrorCodes.BAD_REQUEST_EXCEPTION;
import static camacuchi.keycloak.proxy.util.ErrorCodes.GATEWAY_EXCEPTION;


public class Helpers {

    public static void handleHttpError(ClientHttpResponse response) throws IOException {
        String responseErrorString = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        if (response.getStatusCode().is4xxClientError()) {
            throw new BadRequestException(BAD_REQUEST_EXCEPTION.getStatusCode(), new Throwable(responseErrorString));
        } else if (response.getStatusCode().is5xxServerError()) {
            throw new GatewayException(GATEWAY_EXCEPTION.getStatusCode(), new Throwable(responseErrorString));
        } else {
            throw new RuntimeException("Unhandled HTTP error: " + response.getStatusCode().value());
        }
    }

}
