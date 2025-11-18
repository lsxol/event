package events.authservice.infrastracture.adapters.driving.api.dto;

import events.authservice.domain.model.RoleUzytkownikowEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RejestracjaRequest(@NotBlank(message = "Email jest wymagany")
                                 @Email(message = "Niepoprawny format email")
                                 String email,
                                 @NotBlank(message = "Hasło jest wymagane")
                                 String haslo,
                                 @NotNull(message = "Rola jest wymagana")
                                 RoleUzytkownikowEnum rola) {

}
