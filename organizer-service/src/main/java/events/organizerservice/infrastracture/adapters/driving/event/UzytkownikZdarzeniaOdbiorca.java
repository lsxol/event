package events.organizerservice.infrastracture.adapters.driving.event;

import events.organizerservice.domain.ports.driving.StworzProfilUseCase;
import events.organizerservice.domain.ports.driving.StworzProfilUseCase.StworzProfilCommand;
import events.organizerservice.infrastracture.adapters.driving.event.dto.RejestrowanieUzytkownikaZdarzenie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UzytkownikZdarzeniaOdbiorca {

  private final StworzProfilUseCase stworzProfilUseCase;

  @KafkaListener(topics = "rejestrowanie_uzytkownika", groupId = "organizer-service-group")
  public void stworzProfil(RejestrowanieUzytkownikaZdarzenie rejestrowanieUzytkownikaZdarzenie) {
    log.info("Rejestrowanie uzytkownika: {}", rejestrowanieUzytkownikaZdarzenie);
    var command = new StworzProfilCommand(rejestrowanieUzytkownikaZdarzenie.uzytkownikId(),
        rejestrowanieUzytkownikaZdarzenie.email());
    stworzProfilUseCase.stworzProfil(command);
    log.info("Profil stworzony");
  }
}
