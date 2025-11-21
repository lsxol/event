package events.authservice.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import events.authservice.domain.exception.UzytkownikException;
import events.authservice.domain.model.Email;
import events.authservice.domain.model.HasloHash;
import events.authservice.domain.model.RoleUzytkownikowEnum;
import events.authservice.domain.model.UserId;
import events.authservice.domain.model.Uzytkownik;
import events.authservice.domain.ports.driven.PasswordHasherPort;
import events.authservice.domain.ports.driven.TokenProviderPort;
import events.authservice.domain.ports.driven.UzytkownikRepositoryPort;
import events.authservice.domain.ports.driving.ZalogujUzytkownikaUseCase.ZalogujUzytkownikaCommand;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ZalogujUzytkownikaServiceTest {

  @Mock
  UzytkownikRepositoryPort uzytkownikRepositoryPort;

  @Mock
  PasswordHasherPort passwordHasherPort;

  @Mock
  TokenProviderPort tokenProviderPort;

  @InjectMocks
  private ZalogujUzytkownikaService service;

  @Test
  void powinienZalogowacUzytkownika() {
    String email = "test@test.test";
    String haslo = "silneHaslo12!";
    String wygenerowanyToken = "jwt_token_123";
    Uzytkownik uzytkownik = Uzytkownik.doPobrania(new UserId(UUID.randomUUID()),
        new Email(email),
        new HasloHash("hasloHash"),
        RoleUzytkownikowEnum.KLIENT);
    when(uzytkownikRepositoryPort.findByEmail(new Email(email))).thenReturn(java.util.Optional.of(uzytkownik));
    when(passwordHasherPort.sprawdzHaslo(any(String.class), any(HasloHash.class))).thenReturn(true);
    when(tokenProviderPort.generujToken(uzytkownik)).thenReturn(wygenerowanyToken);

    String token = service.zalogujUzytkownika(new ZalogujUzytkownikaCommand(email, haslo));

    assertEquals(wygenerowanyToken, token);
  }

  @Test
  void powinienRzucicBladBlednegoHasla() {
    String email = "test@test.test";
    Uzytkownik uzytkownik = Uzytkownik.doPobrania(new UserId(UUID.randomUUID()),
        new Email(email),
        new HasloHash("hasloHash!213123"),
        RoleUzytkownikowEnum.KLIENT);
    when(uzytkownikRepositoryPort.findByEmail(new Email(email))).thenReturn(java.util.Optional.of(uzytkownik));
    when(passwordHasherPort.sprawdzHaslo(any(String.class), any(HasloHash.class))).thenReturn(false);
    assertThrows(UzytkownikException.class, () -> service.zalogujUzytkownika(new ZalogujUzytkownikaCommand(email, "cosTa123132!m")));
  }

  @Test
  void powinienRzucicBladBrakuUzytkownika() {
    when(uzytkownikRepositoryPort.findByEmail(any())).thenReturn(java.util.Optional.empty());
    assertThrows(UzytkownikException.class,
        () -> service.zalogujUzytkownika(new ZalogujUzytkownikaCommand("brakHasla@tests.pl", "cosTam123213!")));
  }

}
