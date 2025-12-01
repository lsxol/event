package events.organizerservice.application;

import events.organizerservice.domain.model.Email;
import events.organizerservice.domain.model.ProfilUzytkownika;
import events.organizerservice.domain.model.UzytkownikId;
import events.organizerservice.domain.ports.driven.ProfilUzytkownikaRepositoryPort;
import events.organizerservice.domain.ports.driving.StworzProfilUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class StworzProfilService implements StworzProfilUseCase {

  private final ProfilUzytkownikaRepositoryPort profilUzytkownikaRepositoryPort;

  @Override
  @Transactional
  public void stworzProfil(StworzProfilCommand command) {
    UzytkownikId uzytkownikId = new UzytkownikId(command.uzytkownikId());
    if (!profilUzytkownikaRepositoryPort.existsById(uzytkownikId)) {
      ProfilUzytkownika profilUzytkownika = ProfilUzytkownika.stworzProfilUzytkownika(uzytkownikId, new Email(command.email()));
      profilUzytkownikaRepositoryPort.save(profilUzytkownika);
    }
  }

}
