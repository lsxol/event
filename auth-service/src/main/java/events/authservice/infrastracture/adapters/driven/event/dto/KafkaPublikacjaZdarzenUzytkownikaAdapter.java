package events.authservice.infrastracture.adapters.driven.event.dto;

import events.authservice.domain.model.Email;
import events.authservice.domain.model.UzytkownikId;
import events.authservice.domain.ports.driven.PublikacjaZdarzenUzytkownikaPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaPublikacjaZdarzenUzytkownikaAdapter implements PublikacjaZdarzenUzytkownikaPort {

  private static final String TOPIC = "rejestrowanie_uzytkownika";
  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void publikujEventRejestracjiUzytkownika(UzytkownikId uzytkownikId, Email email) {
    log.info("Publikowanie zdarzenia rejestracji uzytkownika: {}", uzytkownikId);
    var zdarzenie = new RejestrowanieUzytkownikaZdarzenie(uzytkownikId.wartosc(), email.wartosc());
    kafkaTemplate.send(TOPIC, zdarzenie);
  }

}
