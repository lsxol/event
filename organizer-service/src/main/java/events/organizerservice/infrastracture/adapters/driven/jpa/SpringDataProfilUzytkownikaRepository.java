package events.organizerservice.infrastracture.adapters.driven.jpa;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProfilUzytkownikaRepository extends JpaRepository<ProfilUzytkownikaEntity, UUID> {
}
