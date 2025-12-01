package events.organizerservice.domain.ports.driven;

import events.organizerservice.domain.model.ProfilUzytkownika;
import events.organizerservice.domain.model.UzytkownikId;
import java.util.Optional;

public interface ProfilUzytkownikaRepositoryPort {

  ProfilUzytkownika save(ProfilUzytkownika profilUzytkownika);

  Optional<ProfilUzytkownika> findById(UzytkownikId id);

  boolean existsById(UzytkownikId id);

}
