package events.authservice.application;

import events.authservice.domain.exception.UzytkownikExceptionUtil;
import events.authservice.domain.model.Email;
import events.authservice.domain.model.Uzytkownik;
import events.authservice.domain.ports.driven.PasswordHasherPort;
import events.authservice.domain.ports.driven.TokenProviderPort;
import events.authservice.domain.ports.driven.UzytkownikRepositoryPort;
import events.authservice.domain.ports.driving.ZalogujUzytkownikaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ZalogujUzytkownikaService implements ZalogujUzytkownikaUseCase {

  private final UzytkownikRepositoryPort uzytkownikRepositoryPort;
  private final PasswordHasherPort passwordHasherPort;
  private final TokenProviderPort tokenProviderPort;

  @Override
  @Transactional
  public String zalogujUzytkownika(ZalogujUzytkownikaCommand command) {
    Uzytkownik uzytkownik = uzytkownikRepositoryPort.findByEmail(new Email(command.email()))
        .orElseThrow(UzytkownikExceptionUtil.nieZnalezionoUzytkownikaException());
    if (!passwordHasherPort.sprawdzHaslo(command.hasloPlain(), uzytkownik.getHasloHash())) {
      throw UzytkownikExceptionUtil.nieZnalezionoUzytkownikaException().get();
    }
    return tokenProviderPort.generujToken(uzytkownik);
  }

}
