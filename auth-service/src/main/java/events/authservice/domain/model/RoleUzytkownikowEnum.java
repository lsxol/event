package events.authservice.domain.model;

import events.authservice.commons.Enumerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleUzytkownikowEnum implements Enumerator {

  KLIENT("Klient"),
  ADMIN("Admin"),
  ORGANIZATOR("Organizator");

  private final String etykieta;
}
