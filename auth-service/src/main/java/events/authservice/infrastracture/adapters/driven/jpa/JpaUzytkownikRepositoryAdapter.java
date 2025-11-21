package events.authservice.infrastracture.adapters.driven.jpa;

import events.authservice.domain.model.Email;
import events.authservice.domain.model.HasloHash;
import events.authservice.domain.model.RoleUzytkownikowEnum;
import events.authservice.domain.model.UserId;
import events.authservice.domain.model.Uzytkownik;
import events.authservice.domain.ports.driven.UzytkownikRepositoryPort;
import events.authservice.infrastracture.adapters.driven.jpa.UzytkownikEntity.RoleJPA;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUzytkownikRepositoryAdapter implements UzytkownikRepositoryPort {

  private final SpringDataUzytkownikRepository springDataUzytkownikRepository;

  @Override
  public Uzytkownik save(Uzytkownik uzytkownik) {
    UzytkownikEntity entity = mapToEntity(uzytkownik);
    UzytkownikEntity zapisanyUzytkownik = springDataUzytkownikRepository.save(entity);
    return mapToDomain(zapisanyUzytkownik);
  }

  @Override
  public boolean existsByLogin(Email email) {
    return springDataUzytkownikRepository.existsByEmail(email.wartosc());
  }

  @Override
  public Optional<Uzytkownik> findByEmail(Email email) {
    return springDataUzytkownikRepository.findByEmail(email.wartosc())
        .map(this::mapToDomain);
  }

  private Uzytkownik mapToDomain(UzytkownikEntity zapisanyUzytkownik) {
    return Uzytkownik.doPobrania(new UserId(zapisanyUzytkownik.getId()),
        new Email(zapisanyUzytkownik.getEmail()),
        new HasloHash(zapisanyUzytkownik.getPasswordHash()),
        RoleUzytkownikowEnum.valueOf(zapisanyUzytkownik.getRole().name()));
  }

  private static UzytkownikEntity mapToEntity(Uzytkownik uzytkownik) {
    return new UzytkownikEntity(uzytkownik.getId().wartosc(),
        uzytkownik.getEmail().wartosc(),
        uzytkownik.getHasloHash().wartosc(),
        RoleJPA.valueOf(uzytkownik.getRola().name()));
  }

}
