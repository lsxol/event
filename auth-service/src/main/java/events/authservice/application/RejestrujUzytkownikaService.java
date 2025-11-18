package events.authservice.application;

import events.authservice.domain.exception.UzytkownikExceptionUtil;
import events.authservice.domain.model.Email;
import events.authservice.domain.model.HasloHash;
import events.authservice.domain.model.HasloPlain;
import events.authservice.domain.model.Uzytkownik;
import events.authservice.domain.ports.driven.PasswordHasherPort;
import events.authservice.domain.ports.driven.UzytkownikRepositoryPort;
import events.authservice.domain.ports.driving.RejestrujUzytkownikaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RejestrujUzytkownikaService implements RejestrujUzytkownikaUseCase {

  private final UzytkownikRepositoryPort uzytkownikRepositoryPort;
  private final PasswordHasherPort passwordHasherPort;

  @Override
  @Transactional
  public Uzytkownik rejestrujUzytkownika(RejestrujUzytkownikaCommand command) {
    Email email = new Email(command.email());
    if (uzytkownikRepositoryPort.existsByLogin(email)) {
      throw UzytkownikExceptionUtil.uzytkownikOPodanymMejluIstniejeException();
    }
    HasloHash hasloHash = passwordHasherPort.zahashuj(new HasloPlain(command.hasloPlain()));
    Uzytkownik uzytkownik = Uzytkownik.rejestruj(email, hasloHash, command.rola());
    return uzytkownikRepositoryPort.save(uzytkownik);
  }

}
