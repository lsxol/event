package events.authservice.domain.model;

import java.util.UUID;

public record UserId(UUID wartosc) {

  public static UserId generate() {
    return new UserId(UUID.randomUUID());
  }

}
