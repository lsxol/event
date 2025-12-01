package events.organizerservice.domain.model;

import events.organizerservice.domain.exception.OrganizatorExceptionUtil;
import java.util.UUID;

public record UzytkownikId(UUID wartosc) {

  public UzytkownikId {
    if (wartosc == null) {
      throw OrganizatorExceptionUtil.bledneDaneUzytkownikaException();
    }
  }

}

