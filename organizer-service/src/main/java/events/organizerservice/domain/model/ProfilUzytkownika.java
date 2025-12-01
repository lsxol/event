package events.organizerservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfilUzytkownika {

  private final UzytkownikId id;
  private final Email email;
  private NumerTelefonu numerTelefonu;
  private String imie;
  private String nazwisko;
  private String numerDokumentuTozsamosci;

  public static ProfilUzytkownika stworzProfilUzytkownika(UzytkownikId id, Email email) {
    return new ProfilUzytkownika(id, email, null, null, null, null);
  }

  public void uzupelnijDaneUzytkownika(String numerTelefonu, String imie, String nazwisko, String numerDokumentuTozsamosci) {
    this.numerTelefonu = numerTelefonu == null || numerTelefonu.isBlank() ? null : NumerTelefonu.stworz(numerTelefonu);
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.numerDokumentuTozsamosci = numerDokumentuTozsamosci;
  }

}
