package events.authservice.config.security;

import events.authservice.domain.model.Uzytkownik;
import events.authservice.domain.ports.driven.TokenProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class JwtService implements TokenProviderPort {

  private final SecretKey secretKey;
  private final long expirationTime;

  public JwtService(@Value("${application.jwt.secret-key}") String secretKey,
      @Value("${application.jwt.expiration-ms}") long expirationTime) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    this.expirationTime = expirationTime;
  }

  @Override
  public String generujToken(Uzytkownik uzytkownik) {
    return Jwts.builder()
        .subject(uzytkownik.getEmail().wartosc())
        .issuedAt(new Date())
        .expiration(new Date(new Date().getTime() + expirationTime))
        .claim("userId", uzytkownik.getId().wartosc().toString())
        .claim("rola", uzytkownik.getRola())
        .signWith(secretKey)
        .compact();
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public String extractEmail(String token) {
    return extractAllClaims(token).getSubject();
  }

  public boolean isTokenValid(String token, String email) {
    final String tokenEmail = extractEmail(token);
    return (tokenEmail.equals(email)) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }

}
