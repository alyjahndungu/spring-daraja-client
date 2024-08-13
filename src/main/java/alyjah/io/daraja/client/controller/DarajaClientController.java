package alyjah.io.daraja.client.controller;


import alyjah.io.daraja.client.integration.stkpush.response.MpesaExpressResponse;
import alyjah.io.daraja.client.integration.token.response.TokenResponse;
import alyjah.io.daraja.client.payload.request.ExpressPaymentRequest;
import alyjah.io.daraja.client.util.DarajaUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/api/v1/mobiwave",
        produces = {"application/json; charset=utf-8"}
)
@RequiredArgsConstructor
public class DarajaClientController {

    private final DarajaUtil darajaUtil;

    @GetMapping("/token")
    public TokenResponse getClientToken() {
        return darajaUtil.getClientToken();
    }

    @PostMapping("/simulate/mpesa/express")
    public MpesaExpressResponse simulateMpesaExpressTransaction(@Valid @RequestBody ExpressPaymentRequest request) {
        return darajaUtil.simulateMpesaExpressTransaction(request);
    }

}
