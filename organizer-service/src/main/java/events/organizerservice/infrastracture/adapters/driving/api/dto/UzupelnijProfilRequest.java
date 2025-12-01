package events.organizerservice.infrastracture.adapters.driving.api.dto;

import jakarta.validation.constraints.NotBlank;

public record UzupelnijProfilRequest(String numerTelefonu,
                                     @NotBlank(message = "Imię jest wymagane")
                                     String imie,
                                     @NotBlank(message = "Nazwisko jest wymagane")
                                     String nazwisko,
                                     String numerDokumentuTozsamosci) {

}
