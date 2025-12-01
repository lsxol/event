package events.organizerservice.domain.ports.driving;

import events.organizerservice.domain.exception.OrganizatorExceptionUtil;

public interface UzupelnijProfilUseCase {

  void uzupelnijProfil(UzupelnijProfilCommand uzupelnijProfilCommand);

  record UzupelnijProfilCommand(String numerTelefonu, String imie, String nazwisko, String numerDokumentuTozsamosci) {

    public UzupelnijProfilCommand {
      if (imie == null || imie.isBlank() || nazwisko == null || nazwisko.isBlank()) {
        throw OrganizatorExceptionUtil.bledneDaneUzytkownikaException();
      }
    }

  }

}
