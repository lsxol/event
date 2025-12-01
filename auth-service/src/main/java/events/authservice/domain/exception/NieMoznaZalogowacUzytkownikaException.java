package events.authservice.domain.exception;

final class NieMoznaZalogowacUzytkownikaException extends UzytkownikException {

  NieMoznaZalogowacUzytkownikaException() {
    super("Nie można zalogować użytkownika. Brak uzupełnienia wymaganych danych.", KodBledu.BLAD_LOGOWANIA);
  }

}
