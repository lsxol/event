package events.authservice.infrastracture.adapters.driven.event.dto;

import java.util.UUID;

public record RejestrowanieUzytkownikaZdarzenie(UUID uzytkownikId, String email) {

}
