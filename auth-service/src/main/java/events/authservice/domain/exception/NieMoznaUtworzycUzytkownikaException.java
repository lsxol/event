package events.authservice.domain.exception;

final class NieMoznaUtworzycUzytkownikaException extends UzytkownikException {

  NieMoznaUtworzycUzytkownikaException() {
    super("Nie można utworzyć użytkownika. Brak uzupełnienia wymaganych danych.", ErrorCode.BLAD_TWORZENIA);
  }

}
