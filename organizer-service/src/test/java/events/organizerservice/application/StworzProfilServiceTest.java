package events.organizerservice.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import events.organizerservice.domain.model.Email;
import events.organizerservice.domain.model.ProfilUzytkownika;
import events.organizerservice.domain.model.UzytkownikId;
import events.organizerservice.domain.ports.driven.ProfilUzytkownikaRepositoryPort;
import events.organizerservice.domain.ports.driving.StworzProfilUseCase.StworzProfilCommand;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StworzProfilServiceTest {

  private final UUID ID_UZYTKOWNIKA = UUID.randomUUID();
  private final String EMAIL_UZYTKOWNIKA = "test@test.test";
  @Mock
  StworzProfilService service;
  @Mock
  private ProfilUzytkownikaRepositoryPort profilUzytkownikaRepositoryPort;

  @Test
  void powinienStworzycProfil() {
    when(profilUzytkownikaRepositoryPort.findById(new UzytkownikId(ID_UZYTKOWNIKA))).thenReturn(Optional.empty());
    service.stworzProfil(new StworzProfilCommand(ID_UZYTKOWNIKA, EMAIL_UZYTKOWNIKA));
    verify(profilUzytkownikaRepositoryPort, times(1)).save(any(ProfilUzytkownika.class));
  }

  @Test
  void niePowinienStworzycProfilu() {
    when(profilUzytkownikaRepositoryPort.findById(new UzytkownikId(ID_UZYTKOWNIKA)))
        .thenReturn(Optional.of(
            ProfilUzytkownika.stworzProfilUzytkownika(new UzytkownikId(ID_UZYTKOWNIKA), new Email(EMAIL_UZYTKOWNIKA))));
    service.stworzProfil(new StworzProfilCommand(ID_UZYTKOWNIKA, EMAIL_UZYTKOWNIKA));
    verify(profilUzytkownikaRepositoryPort, never()).save(any());
  }

}
