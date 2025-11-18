package events.authservice.domain.exception;

final class NieZnalezionoUzytkownikaException extends UzytkownikException {

  public NieZnalezionoUzytkownikaException(String zmienna, String identyfikator) {
    super(String.format("Nie znaleziono użytkownika o %s: %s.", zmienna, identyfikator), ErrorCode.UZYTKOWNIK_NIE_ZNALEZIONY);
  }

}
