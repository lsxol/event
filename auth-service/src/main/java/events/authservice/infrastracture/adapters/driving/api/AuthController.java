package events.authservice.infrastracture.adapters.driving.api;

import events.authservice.domain.ports.driving.RejestrujUzytkownikaUseCase;
import events.authservice.domain.ports.driving.RejestrujUzytkownikaUseCase.RejestrujUzytkownikaCommand;
import events.authservice.domain.ports.driving.ZalogujUzytkownikaUseCase;
import events.authservice.domain.ports.driving.ZalogujUzytkownikaUseCase.ZalogujUzytkownikaCommand;
import events.authservice.infrastracture.adapters.driving.api.dto.LogowanieRequest;
import events.authservice.infrastracture.adapters.driving.api.dto.RejestracjaRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  private final ZalogujUzytkownikaUseCase zalogujUzytkownikaUseCase;

  @PostMapping(path = "rejestruj")
  public ResponseEntity<Void> zarejestruj(@Valid @RequestBody RejestracjaRequest rejestracjaRequest) {
    RejestrujUzytkownikaCommand command = new RejestrujUzytkownikaCommand(rejestracjaRequest.email(),
        rejestracjaRequest.haslo(),
        rejestracjaRequest.rola());
    rejestrujUzytkownikaUseCase.rejestrujUzytkownika(command);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping(path = "zaloguj")
  public ResponseEntity<String> zaloguj(@Valid @RequestBody LogowanieRequest logowanieRequest) {
    ZalogujUzytkownikaCommand command = new ZalogujUzytkownikaCommand(logowanieRequest.email(),
        logowanieRequest.haslo());
    return ResponseEntity.ok(zalogujUzytkownikaUseCase.zalogujUzytkownika(command));
  }

}
