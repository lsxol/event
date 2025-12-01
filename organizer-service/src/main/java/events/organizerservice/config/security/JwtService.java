package events.organizerservice.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class JwtService {

  private final SecretKey secretKey;

  public JwtService(@Value("${application.jwt.secret-key}") String secretKey) {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    this.secretKey = Keys.hmacShaKeyFor(keyBytes);
  }

  public String pobierzEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public String pobierzRole(String token) {
    return extractClaim(token, claims -> claims.get("rola", String.class));
  }

  public String pobierzUserId(String token) {
    return extractClaim(token, claims -> claims.get("uzytkownikId", String.class));
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
    Claims claims = extractAllClaims(token);
    return claimsFunction.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
  }

  public boolean isTokenValid(String token) {
    try {
      return !isTokenExpired(token);
    } catch (Exception e) {
      return false;
    }
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
}
