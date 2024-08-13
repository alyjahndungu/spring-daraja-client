package alyjah.io.daraja.client.controller;


import alyjah.io.daraja.client.integration.token.response.TokenResponse;
import alyjah.io.daraja.client.util.DarajaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/api/v1/daraja-client",
        produces = {"application/json; charset=utf-8"}
)
@RequiredArgsConstructor
public class DarajaClientController {

    private final DarajaUtil darajaUtil;

    @GetMapping("/token")
    public TokenResponse getClientToken() {
        return darajaUtil.getClientToken();
    }
}
