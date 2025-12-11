package co.penny.dronedelivery.auth.impl;

import co.penny.dronedelivery.auth.service.TokenService;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String HMAC_ALGO = "HmacSHA256";
    private final byte[] secret = "dev-secret-change-me".getBytes(StandardCharsets.UTF_8);

    @Override
    public String issueToken(String subject, String type) {
        try {
            String header = base64UrlUtf8("{\"alg\":\"HS256\",\"typ\":\"JWT\"}");
            long iat = Instant.now().getEpochSecond();
            long exp = iat + 3600;
            String payload = String.format("{\"sub\":\"%s\",\"type\":\"%s\",\"iat\":%d,\"exp\":%d}", subject, type, iat, exp);
            String payloadB = base64UrlUtf8(payload);
            String signingInput = header + '.' + payloadB;
            String sig = base64Url(macSha256(signingInput.getBytes(StandardCharsets.UTF_8)));
            return signingInput + '.' + sig;
        } catch (Exception e) {
            throw new RuntimeException("failed to issue token", e);
        }
    }

    private byte[] macSha256(byte[] data) throws Exception {
        Mac mac = Mac.getInstance(HMAC_ALGO);
        mac.init(new SecretKeySpec(secret, HMAC_ALGO));
        return mac.doFinal(data);
    }

    private static String base64UrlUtf8(String s) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    private static String base64Url(byte[] b) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(b);
    }
}
