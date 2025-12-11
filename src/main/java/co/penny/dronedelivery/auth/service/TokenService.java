package co.penny.dronedelivery.auth.service;

public interface TokenService {
    String issueToken(String subject, String type);
}
