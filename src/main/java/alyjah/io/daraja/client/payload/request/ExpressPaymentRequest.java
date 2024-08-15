package alyjah.io.daraja.client.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ExpressPaymentRequest(
        @NotBlank String phoneNumber,
        @NotNull BigDecimal amount
) {
}
