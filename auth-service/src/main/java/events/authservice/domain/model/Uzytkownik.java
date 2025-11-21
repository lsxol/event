package events.authservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Uzytkownik {

  private final UserId id;
  private final Email email;
  private HasloHash hasloHash;
  private RoleUzytkownikowEnum rola;

  public static Uzytkownik rejestruj(Email email, HasloHash hasloHash, RoleUzytkownikowEnum rola) {
    return new Uzytkownik(UserId.generate(), email, hasloHash, rola);
  }

  public static Uzytkownik doPobrania(UserId userId, Email email, HasloHash hasloHash, RoleUzytkownikowEnum rola) {
    return new Uzytkownik(userId, email, hasloHash, rola);
  }

}
