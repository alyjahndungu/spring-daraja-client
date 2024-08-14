package alyjah.io.daraja.client.controller;


import alyjah.io.daraja.client.integration.stkpush.request.MpesaExpressQueryRequest;
import alyjah.io.daraja.client.integration.stkpush.response.MpesaExpressQueryResponse;
import alyjah.io.daraja.client.integration.stkpush.response.MpesaExpressResponse;
import alyjah.io.daraja.client.integration.stkpush.response.StkCallbackResponse;
import alyjah.io.daraja.client.integration.token.response.TokenResponse;
import alyjah.io.daraja.client.model.MpesaExpressTransactions;
import alyjah.io.daraja.client.payload.request.ExpressPaymentRequest;
import alyjah.io.daraja.client.payload.response.AcknowledgeResponse;
import alyjah.io.daraja.client.util.DarajaUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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

    @PostMapping("/express/simulate")
    public MpesaExpressResponse simulateMpesaExpressTransaction(@Valid @RequestBody ExpressPaymentRequest request) {
        return darajaUtil.simulateMpesaExpressTransaction(request);
    }

    @SneakyThrows
    @PostMapping(path = "/stk-transaction-result", produces = "application/json")
    public ResponseEntity<StkCallbackResponse> stkCallback(@RequestBody StkCallbackResponse stkCallbackResponse) {
        log.info("======= STK Push Async Response =====");
        log.info(stkCallbackResponse.toString());
        return ResponseEntity.ok(stkCallbackResponse);
    }
}
