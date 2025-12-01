package events.authservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Uzytkownik {

  private final UzytkownikId id;
  private final Email email;
  private HasloHash hasloHash;
  private RoleUzytkownikowEnum rola;

  public static Uzytkownik rejestruj(Email email, HasloHash hasloHash, RoleUzytkownikowEnum rola) {
    return new Uzytkownik(UzytkownikId.generate(), email, hasloHash, rola);
  }

  public static Uzytkownik doPobrania(UzytkownikId uzytkownikId, Email email, HasloHash hasloHash, RoleUzytkownikowEnum rola) {
    return new Uzytkownik(uzytkownikId, email, hasloHash, rola);
  }

}
