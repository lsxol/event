package events.organizerservice.infrastracture.adapters.driven.jpa;

import events.organizerservice.domain.model.Email;
import events.organizerservice.domain.model.NumerTelefonu;
import events.organizerservice.domain.model.ProfilUzytkownika;
import events.organizerservice.domain.model.UzytkownikId;
import events.organizerservice.domain.ports.driven.ProfilUzytkownikaRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaProfilUzytkownikaRepositoryAdapter implements ProfilUzytkownikaRepositoryPort {

  private final SpringDataProfilUzytkownikaRepository springDataProfilUzytkownikaRepository;

  @Override
  public ProfilUzytkownika save(ProfilUzytkownika profilUzytkownika) {
    return springDataProfilUzytkownikaRepository.findById(profilUzytkownika.getId().wartosc())
        .map(istniejacyWpis -> {
          istniejacyWpis.zaktualizuj(profilUzytkownika);
          return mapToDomain(istniejacyWpis);
        })
        .orElseGet(() -> {
          ProfilUzytkownikaEntity entity = mapToEntity(profilUzytkownika);
          ProfilUzytkownikaEntity zapisaneEntity = springDataProfilUzytkownikaRepository.save(entity);
          return mapToDomain(zapisaneEntity);
        });
  }

  private ProfilUzytkownika mapToDomain(ProfilUzytkownikaEntity zapisaneEntity) {
    return new ProfilUzytkownika(new UzytkownikId(zapisaneEntity.getId()),
        new Email(zapisaneEntity.getEmail()),
        new NumerTelefonu(zapisaneEntity.getNumerTelefonu()),
        zapisaneEntity.getImie(),
        zapisaneEntity.getNazwisko(),
        zapisaneEntity.getNumerDokumentuTozsamosci());
  }

  private ProfilUzytkownikaEntity mapToEntity(ProfilUzytkownika profilUzytkownika) {
    return new ProfilUzytkownikaEntity(profilUzytkownika.getId().wartosc(),
        profilUzytkownika.getEmail().wartosc(),
        profilUzytkownika.getNumerTelefonu() != null ? profilUzytkownika.getNumerTelefonu().wartosc() : null,
        profilUzytkownika.getImie(),
        profilUzytkownika.getNazwisko(),
        profilUzytkownika.getNumerDokumentuTozsamosci());
  }

  @Override
  public Optional<ProfilUzytkownika> findById(UzytkownikId id) {
    return springDataProfilUzytkownikaRepository.findById(id.wartosc()).map(this::mapToDomain);
  }

  @Override
  public boolean existsById(UzytkownikId id) {
    return springDataProfilUzytkownikaRepository.existsById(id.wartosc());
  }

}
