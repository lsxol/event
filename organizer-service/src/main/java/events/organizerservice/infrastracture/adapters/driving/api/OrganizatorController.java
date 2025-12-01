package events.organizerservice.infrastracture.adapters.driving.api;

import events.organizerservice.domain.ports.driving.UzupelnijProfilUseCase;
import events.organizerservice.domain.ports.driving.UzupelnijProfilUseCase.UzupelnijProfilCommand;
import events.organizerservice.infrastracture.adapters.driving.api.dto.UzupelnijProfilRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("organizator")
class OrganizatorController {

  private final UzupelnijProfilUseCase uzupelnijProfilUseCase;

  @PutMapping(path = "uzupelnij-profil")
  public ResponseEntity<Void> zarejestruj(@Valid @RequestBody UzupelnijProfilRequest uzupelnijProfilRequest) {
    UzupelnijProfilCommand command = new UzupelnijProfilCommand(uzupelnijProfilRequest.numerTelefonu(),
        uzupelnijProfilRequest.imie(),
        uzupelnijProfilRequest.nazwisko(),
        uzupelnijProfilRequest.numerDokumentuTozsamosci());
    uzupelnijProfilUseCase.uzupelnijProfil(command);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
