package events.authservice.domain.ports.driving;

import events.authservice.domain.exception.UzytkownikExceptionUtil;

public interface ZalogujUzytkownikaUseCase {

  String zalogujUzytkownika(ZalogujUzytkownikaCommand command);

  record ZalogujUzytkownikaCommand(String email, String hasloPlain) {

    public ZalogujUzytkownikaCommand {
      if (email == null || email.isBlank() || hasloPlain == null || hasloPlain.isBlank()) {
        throw UzytkownikExceptionUtil.brakWymaganychDanychDoZalogowaniaUzytkownika();
      }
    }
  }

}
