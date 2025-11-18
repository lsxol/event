package events.authservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Uzytkownik {

  private final UserId id;
  private final Email email;
  private HasloHash hasloHash;
  private RoleUzytkownikowEnum role;

  public static Uzytkownik rejestruj(Email email, HasloHash hasloHash, RoleUzytkownikowEnum role) {
    return new Uzytkownik(UserId.generate(), email, hasloHash, role);
  }

  public static Uzytkownik doPobrania(UserId userId, Email email, HasloHash hasloHash, RoleUzytkownikowEnum role) {
    return new Uzytkownik(userId, email, hasloHash, role);
  }

}
