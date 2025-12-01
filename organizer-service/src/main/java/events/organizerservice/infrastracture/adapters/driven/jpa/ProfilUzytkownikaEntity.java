package events.organizerservice.infrastracture.adapters.driven.jpa;

import events.organizerservice.commons.AuditableEntity;
import events.organizerservice.domain.model.ProfilUzytkownika;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "profil_uzytkownika")
@Getter
@NoArgsConstructor
public class ProfilUzytkownikaEntity extends AuditableEntity implements Persistable<UUID> {

  @Id
  private UUID id;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true)
  private String numerTelefonu;

  @Column()
  private String imie;

  @Column()
  private String nazwisko;

  @Column()
  private String numerDokumentuTozsamosci;

  @Transient
  private boolean isNew = true;

  @Override
  public boolean isNew() {
    return isNew;
  }

  @PostLoad
  @PostPersist
  void markNotNew() {
    isNew = false;
  }

  public ProfilUzytkownikaEntity(UUID id, String email, String numerTelefonu, String imie, String nazwisko,
      String numerDokumentuTozsamosci) {
    this.id = id;
    this.email = email;
    this.numerTelefonu = numerTelefonu;
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.numerDokumentuTozsamosci = numerDokumentuTozsamosci;
  }

  void zaktualizuj(ProfilUzytkownika profilUzytkownika) {
    this.numerTelefonu = profilUzytkownika.getNumerTelefonu() != null ? profilUzytkownika.getNumerTelefonu().wartosc() : null;
    this.imie = profilUzytkownika.getImie();
    this.nazwisko = profilUzytkownika.getNazwisko();
    this.numerDokumentuTozsamosci = profilUzytkownika.getNumerDokumentuTozsamosci();
  }

}
