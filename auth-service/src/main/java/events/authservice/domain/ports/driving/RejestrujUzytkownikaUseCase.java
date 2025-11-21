package events.authservice.domain.ports.driving;

import events.authservice.domain.exception.UzytkownikExceptionUtil;
import events.authservice.domain.model.RoleUzytkownikowEnum;

public interface RejestrujUzytkownikaUseCase {

  void rejestrujUzytkownika(RejestrujUzytkownikaCommand command);

  record RejestrujUzytkownikaCommand(String email, String hasloPlain, RoleUzytkownikowEnum rola) {

    public RejestrujUzytkownikaCommand {
      if (email == null || email.isBlank() || hasloPlain == null || hasloPlain.isBlank() || rola == null) {
        throw UzytkownikExceptionUtil.brakWymaganychDanychDoUtworzeniaUzytkownika();
      }
    }

  }

}
