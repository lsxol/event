package events.authservice.infrastracture.security;

import events.authservice.domain.model.HasloHash;
import events.authservice.domain.model.HasloPlain;
import events.authservice.domain.ports.driven.PasswordHasherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptPasswordHasherAdapter implements PasswordHasherPort {

  private final PasswordEncoder passwordEncoder;

  @Override
  public HasloHash zahashuj(HasloPlain haslo) {
    String hashString = passwordEncoder.encode(haslo.wartosc());
    return new HasloHash(hashString);
  }

  @Override
  public boolean sprawdzHaslo(String haslo, HasloHash zahashowanaHaslo) {
    return passwordEncoder.matches(haslo, zahashowanaHaslo.wartosc());
  }


}
