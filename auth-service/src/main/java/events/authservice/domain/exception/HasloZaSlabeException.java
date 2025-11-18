package events.authservice.domain.exception;

final class HasloZaSlabeException extends UzytkownikException {

  public HasloZaSlabeException() {
    super("Hasło musi mieć min. 8 znaków, dużą i małą literę, cyfrę i znak specjalny", ErrorCode.HASLO_ZA_SLABE);
  }

}
