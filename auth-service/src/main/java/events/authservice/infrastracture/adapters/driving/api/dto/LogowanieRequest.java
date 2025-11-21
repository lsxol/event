package events.authservice.infrastracture.adapters.driving.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogowanieRequest(@NotBlank(message = "Email jest wymagany")
                                 @Email(message = "Niepoprawny format email")
                                 String email,
                               @NotBlank(message = "Hasło jest wymagane")
                                 String haslo) {

}
