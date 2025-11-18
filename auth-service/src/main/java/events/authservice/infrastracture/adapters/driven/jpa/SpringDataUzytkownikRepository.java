package events.authservice.infrastracture.adapters.driven.jpa;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUzytkownikRepository extends JpaRepository<UzytkownikEntity, UUID> {

  Optional<UzytkownikEntity> findByEmail(String email);

  boolean existsByEmail(String email);

}
