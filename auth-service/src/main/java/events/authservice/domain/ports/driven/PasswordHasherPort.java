package events.authservice.domain.ports.driven;

import events.authservice.domain.model.HasloHash;
import events.authservice.domain.model.HasloPlain;

public interface PasswordHasherPort {

  HasloHash zahashuj(HasloPlain haslo);

  boolean sprawdzHaslo(String haslo, HasloHash zahashowanaHaslo);

}
