package events.authservice.domain.ports.driven;

import events.authservice.domain.model.Email;
import events.authservice.domain.model.Uzytkownik;
import java.util.Optional;

public interface UzytkownikRepositoryPort {

  Uzytkownik save(Uzytkownik uzytkownik);
  boolean existsByLogin(Email email);
  Optional<Uzytkownik> findByEmail(Email email);

}
