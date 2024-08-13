package alyjah.io.daraja.client.util;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    DATA_VIOLATION_EXCEPTION("DV-001"),
    NOT_FOUND_EXCEPTION("NF-001"),
    GATEWAY_EXCEPTION("GE-001"),
    KEYCLOAK_CLIENT_AUTHORIZATION_EXCEPTION("KA-001"),
    PHONE_NUMBER_MISMATCH_EXCEPTION("PN-001"),
    BAD_REQUEST_EXCEPTION("BR-001");

    final String statusCode;

    ErrorCodes(String statusCode) {
        this.statusCode = statusCode;
    }

}
