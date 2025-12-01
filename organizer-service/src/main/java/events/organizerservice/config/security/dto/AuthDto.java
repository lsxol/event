package events.organizerservice.config.security.dto;

import events.organizerservice.domain.model.Email;
import events.organizerservice.domain.model.UzytkownikId;

public record AuthDto(
    UzytkownikId uzytkownikId,
    Email email
) {

}
