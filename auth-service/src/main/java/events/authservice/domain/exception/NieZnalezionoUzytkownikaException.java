package events.authservice.domain.exception;

final class NieZnalezionoUzytkownikaException extends UzytkownikException {

  public NieZnalezionoUzytkownikaException() {
    super("Nie znaleziono użytkownik", ErrorCode.UZYTKOWNIK_NIE_ZNALEZIONY);
  }

}
