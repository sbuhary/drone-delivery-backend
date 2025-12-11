package co.penny.dronedelivery.auth.controller;

import co.penny.dronedelivery.auth.api.TokenRequest;
import co.penny.dronedelivery.auth.api.TokenResponse;
import co.penny.dronedelivery.auth.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokens;

    public AuthController(TokenService tokens) {
        this.tokens = tokens;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> token(@RequestBody TokenRequest req) {
        // NOTE: this is a dev shim. In production validate credentials.
        String token = tokens.issueToken(req.name(), req.type());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
