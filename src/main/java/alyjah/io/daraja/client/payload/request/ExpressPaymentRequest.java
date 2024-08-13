package alyjah.io.daraja.client.payload.request;

import jakarta.validation.constraints.NotBlank;

public record ExpressPaymentRequest(
        @NotBlank String phoneNumber,
        @NotBlank String amount,
        String description
) {
}
