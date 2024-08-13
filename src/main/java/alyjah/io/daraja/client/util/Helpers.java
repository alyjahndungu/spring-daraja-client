package alyjah.io.daraja.client.util;

import alyjah.io.daraja.client.exception.BadRequestException;
import alyjah.io.daraja.client.exception.GatewayException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import static alyjah.io.daraja.client.util.ErrorCodes.BAD_REQUEST_EXCEPTION;
import static alyjah.io.daraja.client.util.ErrorCodes.GATEWAY_EXCEPTION;
import static java.nio.charset.StandardCharsets.UTF_8;


public class Helpers {

    // Formatter for timestamp
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            .withZone(ZoneId.systemDefault());
    private static final SecureRandom secureRandom = new SecureRandom(); // Thread-safe
    private static final String UPPERCASE_ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    public static void handleHttpError(ClientHttpResponse response) throws IOException {
        String responseErrorString = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        if (response.getStatusCode().is4xxClientError()) {
//            throw new BadRequestException(BAD_REQUEST_EXCEPTION.getStatusCode(), new Throwable(responseErrorString));
            throw new BadRequestException(responseErrorString);
        } else if (response.getStatusCode().is5xxServerError()) {
            throw new GatewayException(GATEWAY_EXCEPTION.getStatusCode(), new Throwable(responseErrorString));
        } else {
            throw new RuntimeException("Unhandled HTTP error: " + response.getStatusCode().value());
        }
    }

    public static String encodePasswordToBase64(String shortCode, String passKey) {
        String timestamp = getTransactionTimestamp();
        String value = String.format("%s%s%s", shortCode, passKey, timestamp);
        return Base64.getEncoder().encodeToString((value).getBytes(UTF_8));
    }

    public static String getTransactionTimestamp() {
        return FORMATTER.format(Instant.now());
    }

    public static String getSecureUniqueString() {
        StringBuilder uniqueString = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            int index = secureRandom.nextInt(UPPERCASE_ALPHANUMERIC_CHARACTERS.length());
            uniqueString.append(UPPERCASE_ALPHANUMERIC_CHARACTERS.charAt(index));
        }
        return uniqueString.toString();
    }
}
