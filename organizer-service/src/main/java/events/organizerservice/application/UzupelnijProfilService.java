package events.organizerservice.application;

import events.organizerservice.config.security.AuthHelper;
import events.organizerservice.domain.model.Email;
import events.organizerservice.domain.model.ProfilUzytkownika;
import events.organizerservice.domain.model.UzytkownikId;
import events.organizerservice.domain.ports.driven.ProfilUzytkownikaRepositoryPort;
import events.organizerservice.domain.ports.driving.UzupelnijProfilUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UzupelnijProfilService implements UzupelnijProfilUseCase {

  private final ProfilUzytkownikaRepositoryPort profilUzytkownikaRepositoryPort;

  @Override
  @Transactional
  public void uzupelnijProfil(UzupelnijProfilCommand uzupelnijProfilCommand) {
    UzytkownikId uzytkownikId = AuthHelper.getAuth().uzytkownikId();
    Email email = AuthHelper.getAuth().email();
    ProfilUzytkownika profilUzytkownika = profilUzytkownikaRepositoryPort.findById(uzytkownikId)
        .orElseGet(() -> ProfilUzytkownika.stworzProfilUzytkownika(uzytkownikId, email));
    profilUzytkownika.uzupelnijDaneUzytkownika(uzupelnijProfilCommand.numerTelefonu(),
        uzupelnijProfilCommand.imie(),
        uzupelnijProfilCommand.nazwisko(),
        uzupelnijProfilCommand.numerDokumentuTozsamosci());
    profilUzytkownikaRepositoryPort.save(profilUzytkownika);
  }

}
