package events.organizerservice.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import events.organizerservice.config.security.dto.SimpleAuthDetails;
import events.organizerservice.domain.model.Email;
import events.organizerservice.domain.model.ProfilUzytkownika;
import events.organizerservice.domain.model.UzytkownikId;
import events.organizerservice.domain.ports.driven.ProfilUzytkownikaRepositoryPort;
import events.organizerservice.domain.ports.driving.UzupelnijProfilUseCase.UzupelnijProfilCommand;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ExtendWith(MockitoExtension.class)
class UzupelnijProfilServiceTest {

  private final UUID ID_UZYTKOWNIKA = UUID.randomUUID();
  private final String EMAIL_UZYTKOWNIKA = "test@test.test";
  private final String IMIE_UZYTKOWNIKA = "Jan";
  private final String NAZWISKO_UZYTKOWNIKA = "Nowak";
  @Mock
  UzupelnijProfilService service;
  @Mock
  private ProfilUzytkownikaRepositoryPort profilUzytkownikaRepositoryPort;

  @BeforeEach
  void ustawSecurity() {
    var uzytkownik = new User(EMAIL_UZYTKOWNIKA, null, List.of());
    var authToken = new UsernamePasswordAuthenticationToken(uzytkownik, null, List.of());
    authToken.setDetails(new SimpleAuthDetails(ID_UZYTKOWNIKA));
    SecurityContextHolder.getContext().setAuthentication(authToken);
  }

  @AfterEach
  void czyscSecurity() {
    SecurityContextHolder.clearContext();
  }

  @Test
  void powinienZaktualizowacIstniejacyProfil() {
    ProfilUzytkownika profilUzytkownika = ProfilUzytkownika.stworzProfilUzytkownika(new UzytkownikId(ID_UZYTKOWNIKA),
        new Email(EMAIL_UZYTKOWNIKA));
    when(profilUzytkownikaRepositoryPort.findById(new UzytkownikId(ID_UZYTKOWNIKA))).thenReturn(Optional.of(profilUzytkownika));
    var command = new UzupelnijProfilCommand(null, IMIE_UZYTKOWNIKA, NAZWISKO_UZYTKOWNIKA, null);
    service.uzupelnijProfil(command);
    verify(profilUzytkownikaRepositoryPort).save(profilUzytkownika);
    assertThat(profilUzytkownika.getImie()).isEqualTo(IMIE_UZYTKOWNIKA);
    assertThat(profilUzytkownika.getNazwisko()).isEqualTo(NAZWISKO_UZYTKOWNIKA);
  }

  @Test
  void powinienUtworzycNowyProfilJesliNieIstnieje() {
    when(profilUzytkownikaRepositoryPort.findById(new UzytkownikId(ID_UZYTKOWNIKA))).thenReturn(Optional.empty());

    var command = new UzupelnijProfilCommand("500123456", IMIE_UZYTKOWNIKA, NAZWISKO_UZYTKOWNIKA, null);

    service.uzupelnijProfil(command);
    ArgumentCaptor<ProfilUzytkownika> captor = ArgumentCaptor.forClass(ProfilUzytkownika.class);
    verify(profilUzytkownikaRepositoryPort).save(captor.capture());

    ProfilUzytkownika zapisanyProfil = captor.getValue();
    assertThat(zapisanyProfil.getId().wartosc()).isEqualTo(ID_UZYTKOWNIKA); // ID z tokena
    assertThat(zapisanyProfil.getEmail().wartosc()).isEqualTo(EMAIL_UZYTKOWNIKA); // Email z tokena
    assertThat(zapisanyProfil.getImie()).isEqualTo(IMIE_UZYTKOWNIKA);
    assertThat(zapisanyProfil.getNumerTelefonu().wartosc()).isEqualTo("+48500123456");
  }

}
