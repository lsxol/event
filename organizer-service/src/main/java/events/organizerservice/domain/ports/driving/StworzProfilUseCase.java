package events.organizerservice.domain.ports.driving;

import events.organizerservice.domain.exception.OrganizatorExceptionUtil;
import java.util.UUID;

public interface StworzProfilUseCase {

  void stworzProfil(StworzProfilCommand command);

  record StworzProfilCommand(UUID uzytkownikId, String email) {

    public StworzProfilCommand {
      if (uzytkownikId == null || email == null || email.isBlank()) {
        throw OrganizatorExceptionUtil.bledneDaneUzytkownikaException();
      }
    }

  }

}

