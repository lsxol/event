package events.authservice.domain.ports.driven;

import events.authservice.domain.model.Uzytkownik;

public interface TokenProviderPort {

  String generujToken(Uzytkownik uzytkownik);
}
