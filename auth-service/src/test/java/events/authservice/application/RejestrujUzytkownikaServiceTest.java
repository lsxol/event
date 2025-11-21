package events.authservice.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import events.authservice.domain.exception.UzytkownikException;
import events.authservice.domain.model.Email;
import events.authservice.domain.model.HasloHash;
import events.authservice.domain.model.HasloPlain;
import events.authservice.domain.model.RoleUzytkownikowEnum;
import events.authservice.domain.model.Uzytkownik;
import events.authservice.domain.ports.driven.PasswordHasherPort;
import events.authservice.domain.ports.driven.UzytkownikRepositoryPort;
import events.authservice.domain.ports.driving.RejestrujUzytkownikaUseCase.RejestrujUzytkownikaCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RejestrujUzytkownikaServiceTest {

  @Mock
  private UzytkownikRepositoryPort uzytkownikRepositoryPort;

  @Mock
  private PasswordHasherPort passwordHasherPort;

  @InjectMocks
  private RejestrujUzytkownikaService service;

  @Test
  void powinienZarejestrowacNowegoUzytkownika() {
    var command = new RejestrujUzytkownikaCommand("email@email.com", "hasloPlain12!", RoleUzytkownikowEnum.KLIENT);
    when(uzytkownikRepositoryPort.existsByLogin(any(Email.class))).thenReturn(false);
    when(passwordHasherPort.zahashuj(any(HasloPlain.class))).thenReturn(new HasloHash("hash"));
    service.rejestrujUzytkownika(command);
    verify(uzytkownikRepositoryPort, times(1)).save(any(Uzytkownik.class));
  }

  @Test
  void niePowinienZarejestrowacNowegoUzytkownikaJesliEmailJestZajety() {
    var command = new RejestrujUzytkownikaCommand("email@email.com", "hasloPlain12!", RoleUzytkownikowEnum.KLIENT);
    when(uzytkownikRepositoryPort.existsByLogin(any(Email.class))).thenReturn(true);
    assertThrows(UzytkownikException.class, () -> service.rejestrujUzytkownika(command));
    verify(uzytkownikRepositoryPort, never()).save(any(Uzytkownik.class));
  }

  @Test
  void niePowinienZarejestrowacUzytkownikaJesliHasloSlabe() {
    var command = new RejestrujUzytkownikaCommand("email@email.com", "s", RoleUzytkownikowEnum.KLIENT);
    assertThrows(UzytkownikException.class, () -> service.rejestrujUzytkownika(command));
    verify(uzytkownikRepositoryPort, never()).save(any(Uzytkownik.class));
    verify(passwordHasherPort, never()).zahashuj(any());
  }

  @Test
  void niePowinienZarejestrowacUzytkownikaJesliEmailZlyFormat() {
    var command = new RejestrujUzytkownikaCommand("email@email", "MocneHaslo12!", RoleUzytkownikowEnum.KLIENT);
    assertThrows(UzytkownikException.class, () -> service.rejestrujUzytkownika(command));
    verify(passwordHasherPort, never()).zahashuj(any());
  }

}
