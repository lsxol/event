package events.authservice.domain.exception;

final class UzytkownikOPodanymMejluIstniejeException extends UzytkownikException {

  public UzytkownikOPodanymMejluIstniejeException() {
    super("Użytkownik o podanym emailu już istnieje.", ErrorCode.EMAIL_ZAJETY);
  }

}
