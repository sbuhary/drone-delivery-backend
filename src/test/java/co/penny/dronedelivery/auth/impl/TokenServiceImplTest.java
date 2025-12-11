package co.penny.dronedelivery.auth.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;

class TokenServiceImplTest {

    @Test
    void issueToken_containsSubAndType() {
        TokenServiceImpl svc = new TokenServiceImpl();
        String token = svc.issueToken("alice", "drone");
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length);
        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        assertTrue(payloadJson.contains("\"sub\":\"alice\""));
        assertTrue(payloadJson.contains("\"type\":\"drone\""));
    }
}
