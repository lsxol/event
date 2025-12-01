package events.authservice.domain.model;

import java.util.UUID;

public record UzytkownikId(UUID wartosc) {

  public static UzytkownikId generate() {
    return new UzytkownikId(UUID.randomUUID());
  }

}
