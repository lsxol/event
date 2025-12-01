package events.organizerservice.infrastracture.adapters.driving.event.dto;

import java.util.UUID;

public record RejestrowanieUzytkownikaZdarzenie(
    UUID uzytkownikId,
    String email
) {

}
