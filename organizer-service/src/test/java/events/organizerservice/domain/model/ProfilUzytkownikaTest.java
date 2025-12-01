package events.organizerservice.domain.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class ProfilUzytkownikaTest {

  @Test
  void powinienUtworzycPustyProfilUzytkownika() {
    UzytkownikId uzytkownikId = new UzytkownikId(UUID.randomUUID());
    Email email = new Email("test@test.test");

    ProfilUzytkownika profilUzytkownika = ProfilUzytkownika.stworzProfilUzytkownika(uzytkownikId, email);
    assertThat(profilUzytkownika.getId()).isEqualTo(uzytkownikId);
    assertThat(profilUzytkownika.getEmail()).isEqualTo(email);
    assertThat(profilUzytkownika.getNazwisko()).isNull();
    assertThat(profilUzytkownika.getImie()).isNull();
    assertThat(profilUzytkownika.getNumerDokumentuTozsamosci()).isNull();
    assertThat(profilUzytkownika.getNumerTelefonu()).isNull();
  }

  @Test
  void powinienUzupelnicProfilUzytkownika() {
    UzytkownikId uzytkownikId = new UzytkownikId(UUID.randomUUID());
    Email email = new Email("test@test.test");
    ProfilUzytkownika profilUzytkownika = ProfilUzytkownika.stworzProfilUzytkownika(uzytkownikId, email);

    String nazwisko = "Kowalski";
    String imie = "Jan";
    String numerDokumentuTozsamosci = "1234567890";
    String numerTelefonu = "123456789";

    profilUzytkownika.uzupelnijDaneUzytkownika(nazwisko, imie, numerDokumentuTozsamosci, numerTelefonu);
    assertThat(profilUzytkownika.getNazwisko()).isEqualTo(nazwisko);
    assertThat(profilUzytkownika.getNumerTelefonu()).isEqualTo("+48" + numerTelefonu);
  }

}
