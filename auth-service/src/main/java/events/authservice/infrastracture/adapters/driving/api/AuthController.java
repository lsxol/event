package events.authservice.infrastracture.adapters.driving.api;

import events.authservice.domain.model.Uzytkownik;
import events.authservice.domain.ports.driving.RejestrujUzytkownikaUseCase;
import events.authservice.domain.ports.driving.RejestrujUzytkownikaUseCase.RejestrujUzytkownikaCommand;
import events.authservice.infrastracture.adapters.driving.api.dto.RejestracjaRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

  private final RejestrujUzytkownikaUseCase rejestrujUzytkownikaUseCase;

  @PostMapping(path = "rejestruj")
  public ResponseEntity<Uzytkownik> zarejestruj(@Valid @RequestBody RejestracjaRequest rejestracjaRequest) {
    RejestrujUzytkownikaCommand command = new RejestrujUzytkownikaCommand(rejestracjaRequest.email(),
        rejestracjaRequest.haslo(),
        rejestracjaRequest.rola());
    return ResponseEntity.ok(rejestrujUzytkownikaUseCase.rejestrujUzytkownika(command));
  }

}
