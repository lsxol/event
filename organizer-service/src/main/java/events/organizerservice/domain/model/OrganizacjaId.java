package events.organizerservice.domain.model;

import java.util.UUID;

public record OrganizacjaId(UUID wartosc) {

  public OrganizacjaId {
    if (wartosc == null) {
      throw new IllegalArgumentException("OrganizationId nie może być null");
    }
  }

}
