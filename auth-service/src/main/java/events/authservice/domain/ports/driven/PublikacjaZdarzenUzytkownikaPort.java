package events.authservice.domain.ports.driven;

import events.authservice.domain.model.Email;
import events.authservice.domain.model.UzytkownikId;

public interface PublikacjaZdarzenUzytkownikaPort {

  void publikujEventRejestracjiUzytkownika(UzytkownikId uzytkownikId, Email email);
}
